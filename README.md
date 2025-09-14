# ARQUITECTURA BASE

## Arquitectura Hexagonal
También conocida como **arquitectura de puertos y adaptadores**, busca aislar la lógica de negocio de cualquier detalle de implementación, como el tipo de base de datos, el *message broker*, entre otros.

Para lograrlo, las capas de **dominio** y **aplicación** se definen en función de **abstracciones** (interfaces que representan los **puertos**).  
Las implementaciones concretas, dependientes de tecnologías específicas, se ubican en la capa de **infraestructura**, representando los **adaptadores**.

!["imagen-hexagonal"](/assets/hexagonal.png)

## Casos de Usos
Los **casos de uso** representan los servicios que el sistema expone para ser consumidos por un usuario final o por otro backend.  
Se pueden clasificar en dos tipos:
- **Command**: modifican el estado del sistema.
- **Query**: consultan información sin alterar el estado.

### Creación de un Caso de Uso (Command)
1. **Crear un objeto de tipo Command**  
   Este tipo de objeto será el encargado de disparar el caso de uso y será recibido por un puerto de entrada de nuestro sistema,  
   como puede ser un controlador REST (request HTTP).  
   Posee la información necesaria para realizar una mutación del estado y debe implementar la interfaz **Command**.

   **Ejemplo:**  
   !["command"](/assets/command.png)

2. **Crear un Handler para el Command**  
   Para crear un *command handler*, que representará nuestro caso de uso, se debe implementar la interfaz **CommandHandler**,  
   la cual posee un genérico con el tipo de *command* que lo disparará.

   Se debe sobrescribir el método **handle**, que será el encargado de contener toda la lógica que resuelve nuestro caso de uso.  
   En esta clase se inyectarán nuestros adaptadores para interactuar con el exterior del servidor.

   El método **handle**, en el caso de un *command*, retorna **void**.

   **Ejemplo:**  
   !["command-handler"](/assets/commandHandler.png)

---

### Creación de un Caso de Uso (Query)
1. **Crear un objeto de tipo Query**  
   Este objeto será el encargado de detonar nuestro caso de uso.  
   A diferencia del objeto *command*, este implementa la interfaz **Query**, la cual es genérica y permite indicar el tipo de dato que retornará el caso de uso una vez ejecutado.

   **Ejemplo:**  
   !["query"](/assets/query.png)

2. **Crear un Handler para el Query**  
   Se debe implementar la interfaz **QueryHandler**, la cual es genérica para:
    - Indicar el tipo de dato del *query* que lo activará.
    - Definir el tipo de dato que deberá responder una vez ejecutado el caso de uso.

   A diferencia del *CommandHandler*, el método **handle** de un *QueryHandler* debe retornar un objeto del tipo de dato especificado en el genérico.

   **Ejemplo:**  
   !["query-handler"](/assets/queryHandler.png)

   
### Disparando un Caso de Uso

El repositorio suministra una clase llamada **UsecaseMediator**, la cual puede ser inyectada en un puerto de entrada, como por ejemplo un controlador.

Mediante su método **dispatch()**, al pasar un objeto de tipo **Query** o **Command**,  
se ejecutará el caso de uso correspondiente en función del tipo de objeto recibido.
!["query-handler"](/assets/dispatch.png)

## Nota Importante

Las clases de las **capa de aplicación** que deban formar parte del **ApplicationContext**,  
para poder ser inyectadas como componentes, deberán estar anotadas con el decorador:

`@ApplicationComponent`

Este decorador es uno **custom** ya disponibilizado en el repositorio y no se debe utilizar uno propio del framework.  
De esta manera, evitamos acoplar la lógica de nuestros casos de uso al framework,  
ya que este último también constituye un **detalle de implementación**.

## Creando un Cliente Http

1. **Definir en la capa de aplicación la interfaz (puerto)**  
   En esta interfaz se declara la definición de los recursos a los que se desea acceder.

   !["http-client"](/assets/httpclient.png)

2. **Desarrollar en la capa de infraestructura el adaptador**  
   Teniendo en cuenta que el repositorio ya tiene configurada la librería **OpenFeign** para implementar clientes HTTP.  
   En el repositorio se incluye un ejemplo donde se consume la API de **Fake Store**.

   !["adapter-http"](/assets/adapterHttp.png)

## Creando un Cliente de Kafka.
1. **Configurando parametros del cliente Kafka.**

2. **definindo topics**

3. **publicando mensajes via kafka**

4. **generando un subscriber a topics**

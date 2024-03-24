# API de Gestión de Clientes 

# Descripción:

Esta API RESTful permite la gestión de clientes y la determinación de su accesibilidad en base a las referencias registradas.

# Funcionalidades:

    Registro de personas:
        Nombre
        Apellido paterno
        Apellido materno
        Fecha de nacimiento
        Dirección (ubicación geográfica, zona/barrio/calle, número de domicilio, referencia)
        Carnet de identidad
    Creación de clientes:
        Email
        Teléfono
        Ocupación
        Validación de edad mínima (20 años)
        Identificador único interno
        Estado inicial: "CREADO"
    Gestión de referencias:
        Añadir o eliminar referencias personales (otros clientes o no)
        Cambio de estado automático:
            ACTIVO: 1 o más referencias
            BLOQUEADO: 0 referencias
        Motivo de eliminación de referencias
    Listado de clientes por accesibilidad:
        Buena:
            Total de referencias >= 2
            Referencias de tipo cliente >= 2
        Regular:
            Total de referencias >= 2
            Referencias de tipo cliente = 0
            Total de referencias = 1
            Referencias de tipo cliente = 1
        Mala:
            Total de referencias = 1
            Referencias de tipo cliente = 0
        Nula:
            Total de referencias = 0
            Referencias de tipo cliente = 0

# Estrategia de desarrollo de software

Enfoque:

    Desarrollo de servicios: Implementación de una arquitectura limpia y escalable para los servicios, adaptándose al crecimiento del proyecto.
    Robustez y escalabilidad: Mayor robustez con capas y separación de la lógica de negocio e infraestructura.
    Aprovechamiento de recursos: Uso de librerías open-source disponibles y patrones de diseño del framework Spring.

Consideraciones:

    Patrones de diseño: Implementación de patrones de diseño y clases específicas de Spring (como JPA) en la lógica de negocio.
    Logs: Se omite la centralización de logs por limitaciones de recursos y contexto del proyecto.
    Mantenimiento: Priorización de la limpieza y claridad del código para facilitar su mantenimiento a largo plazo.

Buenas prácticas:

    Adopción de herramientas open-source: SpotBugs para detección de errores y Checkstyle para el cumplimiento de estándares de codificación.
      - SpotBugs: es un programa para detectar errores en código Java usando análisis estático. Es un programad open-source con licencia LGPL.
      - Checkstyle es una herramienta de desarrollo que ayuda a los programadores a escribir código Java adscrito a estándares de 
      codificación establecidos.
       
    
# Tecnologías:

    Java 17+: Lenguaje de programación
    Spring Data JPA: Persistencia de datos 
    H2: Base de datos en memoria (para pruebas)
    Log4j2

# Frameworks:
    Spring Frameork v3+

# Documentación de la API.
    Swagger: http://<Direccion IP>:8080/api/v1/swagger-ui/swagger-ui/index.html#/

# Manejo de errores:

    Se implementan diferentes códigos de error para identificar la causa del problema.
      - Se tiene un listado de codigos de error, ademas que se encapsulan los errores de logica de negocio 
      y se capturan los errores del sistema para no mostrar informacion que pueda ser usada de mala fe.
    Se proporcionan mensajes de error claros y descriptivos.
      - En este caso se usa log4j para poder imprimir los logs para el monitoreo. En una aplicacion mas robusta 
      se deberia usar tecnologias como OpenTelemetry.

# Persistencia:
 
    H2 se utiliza como base de datos en memoria para pruebas.

# Recomendaciones:

    La API no tiene dependencias de librerías no públicas.
    No requiere instalación de servidor de aplicaciones.
    Las instrucciones para probar la API se encuentran en el archivo README.md del repositorio.


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

# Casos de prueba

    Lista de Personas
    [
        PersonaId: 1, es ClienteId: 1 
        PersonaId: 2, es ClienteId: 2 
        PersonaId: 3, NO es Cliente 
        PersonaId: 4, es ClienteId: 3 
        PersonaId: 5, es ClienteId: 4 
        PersonaId: 6, NO es Cliente 
        PersonaId: 7, es ClienteId: 5 
        PersonaId: 8, es ClienteId: 6 
        PersonaId: 9, NO es Cliente 
        PersonaId: 10, es ClienteId: 7 
        PersonaId: 11, es ClienteId: 8 
        PersonaId: 12, es ClienteId: 9 
        PersonaId: 13, es ClienteId: 10 
    ]

    Lista de Clientes y sus referencias
    
    cliente 1 => 2 referncias que son clientes, PersonasID(2 y 4) 
    cliente 2 => 2 referncias que no son clientes PersonasID(3 y 6) 
    cliente 3 => 2 referncias, un cliente y otro no cliente PersonasID(1 y 6) 
    cliente 4 => 3 referncias, 1 cliente y 2 no clientes PersonasID(1, 3 y 6) 
    cliente 5 => 4 referncias, 2 clientes y 2 no cliente PersonasID(1, 2, 3 y 6) 
    cliente 6 => 1 referncias 1 cliente PersonasID(2) 
    cliente 7 => 3 referncias que son clientes PersonasID(1, 5 y 8) 
    cliente 8 => 3 referncias que no son clientes PersonasID(3, 6 y 9) 
    cliente 9 => 1 referncias 1 no cliente PersonasID(9) 
    cliente 10 => 0 referncias 

![Imagen](/images/CasosPrueba.png)

    Accesibilidad = BUENA
    Regla 1 = TOTAL REFERENCIAS (>= 2) y REFERENCIAS DE TIPO CLIENTE (>=2)
    Regla 2 = TOTAL REFERENCIAS (>= 3) y REFERENCIAS DE TIPO CLIENTE (>=1)
    
    Los clientes a retornar son: 
    [
        cliente ID = 1 (regla 1) PersonasID(2, 4) , 
        cliente ID = 4 (regla 2) PersonasID(1, 3, , 6) , 
        cliente ID = 5 (regla 1 y 2) PersonasID(1, 2, 3 , 6),
        cliente ID = 7 (regla 1 y 2) PersonasID(1, 5 , 8) , 
    ]

    
    Accesibilidad = REGULAR
    Regla 1 = TOTAL REFERENCIAS (>= 2) y REFERENCIAS DE TIPO CLIENTE (0)
    Regla 2 = TOTAL REFERENCIAS (1) y REFERENCIAS DE TIPO CLIENTE (1)
    
    Los clientes a retornar son: 
    [
        cliente ID = 2 (regla 1) PersonasID(3, 6) , 
        cliente ID = 6 (regla 2) PersonasID(1) , 
        cliente ID = 8 (regla 1) PersonasID(3, 6, 9) , 
    ]

    
    Accesibilidad = MALA
    Regla 1 = TOTAL REFERENCIAS (1) y REFERENCIAS DE TIPO CLIENTE (0)
    
    Los clientes a retornar son: 
    [
        cliente ID = 9 (regla 1) PersonasID(9)
    ]

    Accesibilidad = NULA
    Regla 1 = TOTAL REFERENCIAS (0) y REFERENCIAS DE TIPO CLIENTE (0)

    Los clientes a retornar son: 
    [
        cliente ID = 10 (regla 1) 0 referncias
    ]


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
![Imagen](/images/SpotBugs.png)

      - Checkstyle es una herramienta de desarrollo que ayuda a los programadores a escribir código Java adscrito a estándares de 
      codificación establecidos.
![Imagen](/images/Checklist-Style.png)

      - Jacoco: genera un reporte de covertura de codigo por test.
![Imagen](/images/JacocoPorcentajeCovertura.png)
    
# Tecnologías:

    Java 17+: Lenguaje de programación
    Spring Data JPA: Persistencia de datos 
    H2: Base de datos en memoria (para pruebas)
    Log4j2
    Docker

# Frameworks:
    Spring Frameork v3+

# Documentación de la API.
    Swagger: http://<Direccion IP>:8080/api/v1/swagger-ui/swagger-ui/index.html#/

    *Se adicio un servicio de prueba para poder restablecer los datos de prueba 
    
    Datos de pruebas API 
    APIs para cargar datos de prueba.

![Imagen](/images/DocumentacionAPI.png)


# Manejo de errores:

    Se implementan diferentes códigos de error para identificar la causa del problema.
      - Se tiene un listado de codigos de error, ademas que se encapsulan los errores de logica de negocio 
      y se capturan los errores del sistema para no mostrar informacion que pueda ser usada de mala fe.
    Se proporcionan mensajes de error claros y descriptivos.
      - En este caso se usa log4j para poder imprimir los logs para el monitoreo. En una aplicacion mas robusta 
      se deberia usar tecnologias como OpenTelemetry.

# Persistencia:
 
    H2 se utiliza como base de datos en memoria para pruebas.

# Pasos para levantar desplegarlo en un container de Docker:

    Construccion de la imagen de docker con el dockerfile
    
![Imagen](/images/DockerBuildImage.png)

    Lista de imagenes de docker

![Imagen](/images/DockerImagenes.png)

    Lanzamos el contenedor docker con la imagen creada

![Imagen](/images/DockerRunContainer.png)

    Lista de containers de docker
![Imagen](/images/DockeristContainers.png)



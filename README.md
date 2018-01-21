# almundoAsync
Test almundo con Dispacher asíncrono 

# Consigna
Existe un call center donde hay 3 tipos de empleados: operador,
supervisor y director. El proceso de la atención de una llamada
telefónica en primera instancia debe ser atendida por un operador, si
no hay ninguno libre debe ser atendida por un supervisor, y de no
haber tampoco supervisores libres debe ser atendida por un director.

# Requerimientos

Diseñar el modelado de clases y diagramas UML necesarios
para documentar y comunicar el diseño.
Debe existir una clase Dispatcher encargada de manejar las

llamadas, y debe contener el método dispatchCall para que las
asigne a los empleados disponibles.
La clase Dispatcher debe tener la capacidad de poder procesar
10 llamadas al mismo tiempo (de modo concurrente).
Cada llamada puede durar un tiempo aleatorio entre 5 y 10
segundos.
Debe tener un test unitario donde lleguen 10 llamadas.

# Extras/Plus

Dar alguna solución sobre qué pasa con una llamada cuando no
hay ningún empleado libre.
Dar alguna solución sobre qué pasa con una llamada cuando
entran más de 10 llamadas concurrentes.
Agregar los tests unitarios que se crean convenientes.
Agregar documentación de código.

# Solucion

El dispacher funciona como un hilo independiente y completamente asincrónico al hilo principal.

El dispacher es un Singelton,  con esto se previene que se creen otros dispacher, de esta manera existe uno solo corriendo. 

El dispacher usa executorService de java para manejar los hilos y así asegurar que existan solo 10 llamadas simultáneas.

Existe otro executor que verifica si cada uno de los empleados terminó su llamada y lo devuelven a la cola de empleados disponibles, también de 10 hilos máximo. Esto ayuda a que no queden hilos vivos sin nada que hacer.

Al executor  se le puede asignar que cada tanto tiempo verifique si hay alguna llamada en cola y la asigna al próximo empleado. Si no hay empleados disponibles las deja en cola y espera que exista algún empleado disponible,  es decir la llamada queda en una cola.

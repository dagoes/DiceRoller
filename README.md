Aplicacion que simula el lanzamiento de dados.

Uso:

Primer boton te lleva a una pantalla con dados preconfigurados, al elegir uno llevara a la pantalla de lanzamiento.

Segundo boton te lleva a una pantalla que muestra todos los grupos almacandos en la base de dados y un boton para crear mas grupos que llevara a la pantalla de edicion.
Al clickar en un grupo te da las opciones de lanzar, editar o borrar. Lanzar llevara a la pantalla de lanzamiento. Editar llevara a la pantalla de edicion. Borrar elimina el grupo de la base de datos.

La pantalla de edicion presenta un formulario que permite crear nuevos grupos o ver y modificar los valores almacenados. Un boton permite guardar los cambios al salir y el otro sale directamente.

El tercer boton esta desactivado por defecto y lleva directamente a la pantalla de lanzamiento con los ultimos parametros usados. Se activa la primera vez que se entra a esa pantalla.

En la pantalla de lanzado al pulsar el boton Lanzar se realizara la generacion de numeros aletorios con la cantidad y rango recibidos a traves de SharedPreferences.

El cuarto boton lleva a una pantalla con tres opciones: si la pantalla de lanzamiento hace un sonido al lanzar los dados, cambiar el tema a oscuro o claro, borrar todos los datos almacenados(BBDD y SharedPreferences).
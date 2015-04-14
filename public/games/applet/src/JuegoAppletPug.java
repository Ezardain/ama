/**
 * JuegoAppletPug
 *
 * Anima un Pug y con las flechas se puede mover y cambia la velocidad 
 * dependiendo de como se presionan las flechas. Al chocar cambia de imagen 
 * por 2 seg.
 *
 * @author  Mauro Amarantea  A01191903
 * @date    1/21/2015
 * @version 1.0
 */
 
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class JuegoAppletPug extends Applet implements Runnable, KeyListener {
    // Se declaran las variables y objetos
    // direccion en la que se mueve el Pug
    // 1-arriba,2-abajo,3-izquierda y 4-derecha
    private int iDireccion;  
    //variable que controla el cambio de imagen al chocar
    private boolean bChoco;        
    //variable que mantiene el control de la velocidad del Pug
    private int iVelocidad;   
    //variable que mantiene el cambio de imagen
    private int iContadorPaint;     
    // Objeto AudioClip sonido Pug
    private AudioClip aucSonidoPug;     
    // Objeto de la clase Pug
    private Animal aniPug;         
    /* objetos para manejar el buffer del Applet y este no parpadee */
    private Image    imaImagenApplet;   // Imagen a proyectar en Applet	
    private Graphics graGraficaApplet;  // Objeto grafico de la Imagen
	
    /** 
     * init
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos
     * a usarse en el <code>Applet</code> y se definen funcionalidades.
     * 
     */
    public void init() {
        // hago el applet de un tamaño 500 x 500
        setSize(500,500);
        
        // posicion en 4 para que el Pug se mueva a la derecha al inciar
    	iDireccion = 4;
        
        // Velocidad incial del Pug
        iVelocidad = 1;
        
        //Contador que permite el cambio de imagen por algunos seg.
        iContadorPaint = 0;
        
        // el Pug empieza el juego sin haber chocado
        bChoco = false;
        
        // se posicion el Pug en alguna parte al azar del cuadrante 
        // superior izquierdo
	int iPosX = (int) (Math.random() *(getWidth() / 4));    
        int iPosY = (int) (Math.random() *(getHeight() / 4));  
	URL urlImagenPug = this.getClass().getResource("pugRunLeft.gif");
        
        // se crea el objeto Pug
	aniPug = new Animal(iPosX,iPosY,
                    Toolkit.getDefaultToolkit().getImage(urlImagenPug));

	//creo el sonido del Pug
	URL urlSonidoPug = this.getClass().getResource("pugBark.wav");
        aucSonidoPug = getAudioClip (urlSonidoPug);

        // se define el background en color gris
	setBackground (Color.gray);
        
        /* se le añade la opcion al applet de ser escuchado por los eventos
           del teclado  */
	addKeyListener(this);
    }
	
    /** 
     * start
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se crea e inicializa el hilo
     * para la animacion este metodo es llamado despues del init o 
     * cuando el usuario visita otra pagina y luego regresa a la pagina
     * en donde esta este <code>Applet</code>
     * 
     */
    public void start () {
        // Declaras un hilo
        Thread th = new Thread (this);
        // Empieza el hilo
        th.start ();
    }
	
    /** 
     * run
     * 
     * Metodo sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, que contendrá las instrucciones
     * de nuestro juego.
     * 
     */
    public void run () {
        /* mientras dure el juego, se actualizan posiciones de jugadores
           se checa si hubo colisiones para desaparecer jugadores o corregir
           movimientos y se vuelve a pintar todo
        */ 
        while (true) {
            actualiza();
            checaColision();
            repaint();
            try	{
                // El thread se duerme.
                Thread.sleep (20);
            }
            catch (InterruptedException iexError) {
                System.out.println("Hubo un error en el juego " + 
                    iexError.toString());
            }
	}
    }
	
    /** 
     * actualiza
     * 
     * Metodo que actualiza la posicion del objeto Pug al igual que el contador 
     * de cambio de imagen si es que choco
     * 
     */
    public void actualiza() {
        //Dependiendo de la iDireccion del Pug es hacia donde se mueve.
        switch(iDireccion) {
            case 1: { //se mueve hacia arriba
                aniPug.setY(aniPug.getY() - iVelocidad);
                break;    
            }
            case 2: { //se mueve hacia abajo
                aniPug.setY(aniPug.getY() + iVelocidad);
                break;    
            }
            case 3: { //se mueve hacia izquierda
                aniPug.setX(aniPug.getX() - iVelocidad);
                break;    
            }
            case 4: { //se mueve hacia derecha
                aniPug.setX(aniPug.getX() + iVelocidad);
                break;    	
            }
        }
        
        //Si Pug choco actualizar contador
        if(bChoco) {
            iContadorPaint = 40; 
            bChoco = false;
        }
    }
	
    /**
     * checaColision
     * 
     * Metodo usado para checar la colision del objeto Pug
     * con las orillas del <code>Applet</code>.
     * 
     */
    public void checaColision() {
        //Colision del Pug con el Applet dependiendo a donde se mueve.
        switch(iDireccion) {
            case 1: { // si se mueve hacia arriba 
                if(aniPug.getY() < 0) { // y esta pasando el limite
                    iDireccion = 2;     // se cambia la direccion para abajo
                    aucSonidoPug.play();   // ladra el Pug
                    bChoco = true;      //Choco el Pug con una pared
                }
                break;    	
            }     
            case 2: { // si se mueve hacia abajo
                // y se esta saliendo del applet
                if(aniPug.getY() + aniPug.getAlto() > getHeight()) {
                    iDireccion = 1;     // se cambia la direccion para arriba
                    aucSonidoPug.play();   // ladra el Pug
                    bChoco = true;      //Choco el Pug con una pared
                }
                break;    	
            } 
            case 3: { // si se mueve hacia izquierda 
                if(aniPug.getX() < 0) { // y se sale del applet
                    iDireccion = 4;       // se cambia la direccion a la derecha
                    aucSonidoPug.play();     // ladra el Pug
                    bChoco = true;      //Choco el Pug con una pared
                }
                break;    	
            }    
            case 4: { // si se mueve hacia derecha 
                // si se esta saliendo del applet
                if(aniPug.getX() + aniPug.getAncho() > getWidth()) { 
                    iDireccion = 3;       // se cambia direccion a la izquierda
                    aucSonidoPug.play();     // ladra el Pug
                    bChoco = true;      //Choco el Pug con una pared
                }
                break;    	
            }			
        }
    }
	
    /**
     * update
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor y 
     * define cuando usar ahora el paint
     * 
     * @param graGrafico es el <code>objeto grafico</code> usado para dibujar.
     * 
     */
    public void update (Graphics graGrafico) {
        // Inicializan el DoubleBuffer
        if (imaImagenApplet == null) {
                imaImagenApplet = createImage (this.getSize().width, 
                        this.getSize().height);
                graGraficaApplet = imaImagenApplet.getGraphics ();
        }

        // Actualiza la imagen de fondo.
        graGraficaApplet.setColor (getBackground ());
        graGraficaApplet.fillRect (0, 0, this.getSize().width, 
                this.getSize().height);

        // Actualiza el Foreground.
        graGraficaApplet.setColor (getForeground());
        paint(graGraficaApplet);

        // Dibuja la imagen actualizada
        graGrafico.drawImage (imaImagenApplet, 0, 0, this);
    }

    /**
     * keyPressed
     * 
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al dejar presionada
     * alguna tecla.
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al 
     * presionar.
     * 
     */
    public void keyPressed(KeyEvent keyEvent) {
       // no hay codigo pero se debe escribir el metodo
    }
    
    /**
     * keyTyped
     * 
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una 
     * tecla que no es de accion.
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al 
     * presionar.
     * 
     */
    public void keyTyped(KeyEvent keyEvent) {
    	// no hay codigo pero se debe escribir el metodo
    }
    
    /**
     * keyReleased
     * Metodo sobrescrito de la interface <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar la tecla.
     * En este metodo lo que se hace es actualizar la velocidad  del Pug
     * 
     * @param keyEvent es el <code>KeyEvent</code> que se genera en al soltar.
     * 
     */
    public void keyReleased(KeyEvent keyEvent) {
    	// si presiono flecha para arriba
        if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            //si la direccion anterior era arriba, incrementa velocidad en 1
            if(iDireccion == 1) {
                iVelocidad += 1;
            }
            // si la direccion era abajo disminuye velocidad en 1
            else if(iDireccion == 2) {
                iVelocidad -= 1;
            }

            // si la velocidad llega a cero o menos cambia la dirección arriba
            if(iVelocidad <= 0 || iDireccion == 3 || iDireccion == 4 ) {
                iDireccion = 1; 
                iVelocidad = 1;
            }
        }
        // si presiono flecha para abajo
        else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {  
            //si la direccion anterior era abajo, incrementa velocidad en 1
            if(iDireccion == 2) {
                iVelocidad += 1;
            }
            // si la direccion era abajo disminuye velocidad en 1
            else if(iDireccion == 1)
                iVelocidad -= 1;

             // si la velocidad llega a cero o menos cambia la dirección abajo
            if(iVelocidad <= 0 || iDireccion == 3 || iDireccion == 4 ){
                iDireccion = 2; 
                iVelocidad = 1;
            }
        }
        // si presiono flecha a la izquierda
        else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {    
            //si la direccion anterior era izquierda, incrementa velocidad en 1
            if(iDireccion == 3) {
                iVelocidad += 1;
            }
            // si la direccion era derecha disminuye velocidad en 1
            else if(iDireccion == 4)
                iVelocidad -= 1;

             /* si la velocidad llega a cero o menos cambia la dirección 
                izquierda */
            if(iVelocidad <= 0 || iDireccion == 1 || iDireccion == 2 ){
                iDireccion = 3; 
                iVelocidad = 1;
            }
        }
        // si presiono flecha a la derecha
        else if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){    
            //si la direccion anterior era derecha, incrementa velocidad en 1
            if(iDireccion == 4) {
                iVelocidad += 1;
            }
            // si la direccion era izquierda disminuye velocidad en 1
            else if(iDireccion == 3)
                 iVelocidad -= 1;

             // si la velocidad llega a cero o menos cambia la dirección derecha
            if(iVelocidad <= 0 || iDireccion == 1 || iDireccion == 2 ){
                iDireccion = 4; 
                iVelocidad = 1;
            }
        }
    }
    
    /**
     * paint
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada,
     * ademas que cuando la imagen es cargada te despliega una advertencia.
     * 
     * @param graDibujo es el objeto de <code>Graphics</code> usado para 
     * dibujar.
     * 
     */
    public void paint(Graphics graDibujo) {
        if (aniPug != null) {
            //Dibuja la imagen del Pug en el Applet
            //Si contador es mayor de 0 definir imagen nueva y cambiarla
            if(iContadorPaint > 0) {
                //Definir nueva imagen
                URL urlImagenPugChoco =     
                        this.getClass().getResource("pugBark.gif");
                //Cambiar imagen
                aniPug.setImagen( 
                        Toolkit.getDefaultToolkit().getImage(
                        urlImagenPugChoco));
                
                //Reducir contador
                iContadorPaint--;
            }
            //Si contador ya termino regresar a imagen original
            else {
                //Deinfir imagen original
                URL urlImagenPug =     
                        this.getClass().getResource("pugRun.gif");
                //Cambiar a imagen original
                aniPug.setImagen( 
                        Toolkit.getDefaultToolkit().getImage(
                        urlImagenPug));
            }
            
            // si la imagen ya se cargo
            aniPug.paint(graDibujo, this);   
        }// sino se ha cargado se dibuja un mensaje 
        else {
                //Da un mensaje mientras se carga el dibujo	
                graDibujo.drawString("No se cargo la imagen..", 20, 20);
        }
    }
}
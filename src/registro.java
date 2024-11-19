import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class registro {
    private final ArrayList<estudiante> estudiantes;

    public registro() {
        estudiantes = new ArrayList<>();
    }

    public void agregarEstudiantes(String nombre, int edad, String matricula, double notaMedia, boolean matriculado) {
        estudiante nuevoEstudiante = new estudiante(nombre, edad, matricula, notaMedia, matriculado);
        estudiantes.add(nuevoEstudiante);
    }

    public void EliminarEstudiantes(String matricula) {
        for(int i = 0; i < estudiantes.size(); i++) {
            if(estudiantes.get(i).getMatricula().equals(matricula)) {
                estudiantes.remove(i);
                break;
            }
        }
    }

    public void mostrarEstudiantes() {
        for (estudiante est: estudiantes) {
            System.out.println(est);
        }
    }


    public void guardarTexto(String nombreArchivo) throws IOException {
        BufferedWriter bfwriter = new BufferedWriter(new FileWriter(nombreArchivo)) ;
        for (estudiante est : estudiantes) {
            bfwriter.write(est.toString());
            bfwriter.newLine();

        }
        bfwriter.close();
    }


    public void cargarTexto(String nombreArchivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null ) {
                System.out.println(linea);
            }
        }
    }


    public void guardarBinario(String nombreArchivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            out.writeObject(estudiantes);
        }
    }


    public void cargarBinario(String nombreArchivo) throws IOException, ClassNotFoundException {
        ArrayList<estudiante> estudiantes = new ArrayList<>();
        try (ObjectInputStream  dInput = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArrayList<estudiante> estudiantesCargados = (ArrayList<estudiante>) dInput.readObject();
            estudiantes.clear();
            estudiantes.addAll(estudiantesCargados);
        }
        for (estudiante est : estudiantes) {
            System.out.println(est);
        }

    }


    public void guardarXML(String nombreArchivo) throws ParserConfigurationException, TransformerException {



        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element raiz = doc.createElement("Estudiantes");
        doc.appendChild(raiz);

        for (estudiante est : estudiantes) {
            Element estudianteElement = doc.createElement("Estudiante");

            Element nombreElement = doc.createElement("Nombre");
            nombreElement.appendChild(doc.createTextNode(est.getNombre()));
            estudianteElement.appendChild(nombreElement);

            Element edadElement = doc.createElement("Edad");
            edadElement.appendChild(doc.createTextNode(String.valueOf(est.getEdad())));
            estudianteElement.appendChild(edadElement);

            Element matriculaElement = doc.createElement("Matricula");
            matriculaElement.appendChild(doc.createTextNode(est.getMatricula()));
            estudianteElement.appendChild(matriculaElement);

            Element notaMediaElement = doc.createElement("NotaMedia");
            notaMediaElement.appendChild(doc.createTextNode(String.valueOf(est.getNotaMedia())));
            estudianteElement.appendChild(notaMediaElement);

            Element matriculadoElement = doc.createElement("Matriculado");
            matriculadoElement.appendChild(doc.createTextNode(String.valueOf(est.isMatriculado())));
            estudianteElement.appendChild(matriculadoElement);

            raiz.appendChild(estudianteElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(nombreArchivo));
        transformer.transform(source, result);

    }


    public void cargarXML(String nombreArchivo) throws ParserConfigurationException, IOException, SAXException {


        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(nombreArchivo);


        NodeList nList = doc.getElementsByTagName("Estudiante");
        estudiantes.clear();

        for (int i = 0; i < nList.getLength(); i++) {
            Element estudianteElement = (Element) nList.item(i);

            String nombre = estudianteElement.getElementsByTagName("Nombre").item(0).getTextContent();
            int edad = Integer.parseInt(estudianteElement.getElementsByTagName("Edad").item(0).getTextContent());
            String matricula = estudianteElement.getElementsByTagName("Matricula").item(0).getTextContent();
            double notaMedia = Double.parseDouble(estudianteElement.getElementsByTagName("NotaMedia").item(0).getTextContent());
            boolean matriculado = Boolean.parseBoolean(estudianteElement.getElementsByTagName("Matriculado").item(0).getTextContent());

            estudiante nuevoEstudiante = new estudiante(nombre, edad, matricula, notaMedia, matriculado);
            estudiantes.add(nuevoEstudiante);

            boolean existe = false;
            for (estudiante est : estudiantes) {
                if (est.getMatricula().equals(matricula)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                nuevoEstudiante = new estudiante(nombre, edad, matricula, notaMedia, matriculado);
                estudiantes.add(nuevoEstudiante);
            }
        }



        for(estudiante est : estudiantes){
            System.out.println(est);
        }
    }
}
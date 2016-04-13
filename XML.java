import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XML {
	public static void main(String args[]) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		String raiz = null;
		String[][] datos = new String[4][2];
		String[][] datos2 = new String[12][2];
		LinkedList<String> lista = new LinkedList<String>();
		boolean escribir = false;		
		XML xml = new XML();
		int numFichero = 1;
		
		for(String[] aux : datos) {
			aux[0] = "-1";
			aux[1] = "-1";
		}
		for(String[] aux2 : datos2) {
			aux2[0] = "-1";
			aux2[1] = "-1";
		}

		try {
			XMLEventReader reader = factory.createXMLEventReader(new FileInputStream("C:/Users/Alvaro Alonso/Downloads/FullOct2007.xml/FullOct2007.xml"));

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if (event.isStartElement()) {
					StartElement element = event.asStartElement();
					if (element.getName().getLocalPart().equals("vespaadd"))
						raiz = element.getName().toString();

					if (element.getName().getLocalPart().equals("uri")) {
						event = reader.nextEvent();
						datos[0][0] = element.getName().getLocalPart();
						datos[0][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("subject")) {
						event = reader.nextEvent();
						datos[1][0] = element.getName().getLocalPart();
						datos[1][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("content")) {
						event = reader.nextEvent();
						datos[2][0] = element.getName().getLocalPart();
						datos[2][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("bestanswer")) {
						event = reader.nextEvent();
						datos[3][0] = element.getName().getLocalPart();
						datos[3][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("answer_item")) {
						event = reader.nextEvent();
						lista.add(event.asCharacters().getData());
					}

					if (element.getName().getLocalPart().equals("cat")) {
						event = reader.nextEvent();
						datos2[0][0] = element.getName().getLocalPart();
						datos2[0][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("maincat")) {
						event = reader.nextEvent();
						datos2[1][0] = element.getName().getLocalPart();
						datos2[1][1] = event.asCharacters().getData();

						if (event.asCharacters().getData().equalsIgnoreCase("Automóviles y transporte")) {
						//if (event.asCharacters().getData().equalsIgnoreCase("Viajes")) {
							escribir = true;
						}
					}

					if (element.getName().getLocalPart().equals("subcat")) {
						event = reader.nextEvent();
						datos2[2][0] = element.getName().getLocalPart();
						datos2[2][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("date")) {
						event = reader.nextEvent();
						datos2[3][0] = element.getName().getLocalPart();
						datos2[3][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("res_date")) {
						event = reader.nextEvent();
						datos2[4][0] = element.getName().getLocalPart();
						datos2[4][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("vot_date")) {
						event = reader.nextEvent();
						datos2[5][0] = element.getName().getLocalPart();
						datos2[5][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("lastanswerts")) {
						event = reader.nextEvent();
						datos2[6][0] = element.getName().getLocalPart();
						datos2[6][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("qlang")) {
						event = reader.nextEvent();
						datos2[7][0] = element.getName().getLocalPart();
						datos2[7][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("qintl")) {
						event = reader.nextEvent();
						datos2[8][0] = element.getName().getLocalPart();
						datos2[8][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("language")) {
						event = reader.nextEvent();
						datos2[9][0] = element.getName().getLocalPart();
						datos2[9][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("id")) {
						event = reader.nextEvent();
						datos2[10][0] = element.getName().getLocalPart();
						datos2[10][1] = event.asCharacters().getData();
					}

					if (element.getName().getLocalPart().equals("best_id")) {
						event = reader.nextEvent();
						datos2[11][0] = element.getName().getLocalPart();
						datos2[11][1] = event.asCharacters().getData();
					}
				}

				if(event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart().equals("vespaadd") && escribir) {							
						String fichero = "Automoviles" + numFichero + ".xml";
						//String fichero = "Viajes" + numFichero + ".xml";
						numFichero += 1;
						xml.escribirXML(fichero, raiz, datos, lista, datos2);
						actualizar(lista, datos, datos2);
						escribir = false;
					} else if (endElement.getName().getLocalPart().equals("vespaadd")) {
						actualizar(lista, datos, datos2);
					}
				}
			}
		} catch (XMLStreamException | FileNotFoundException e) {
			// TODO: handle exception
		}
	}

	public static void actualizar(LinkedList<String> lista, String[][] datos, String[][] datos2) {
		for (String[] aux : datos)
			aux[1] = "-1";
		for (String[] aux2 : datos2)
			aux2[1] = "-1";
		while (!lista.isEmpty())
			lista.removeFirst();
	}

	public void escribirXML(String fichero, String raiz, String[][] datos, LinkedList<String> lista,
			String[][] datos2) {
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

		try {
			XMLEventWriter writer = outputFactory.createXMLEventWriter(new FileOutputStream(fichero), "UTF-8");
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent fin = eventFactory.createDTD("\n");
			XMLEvent tab = eventFactory.createDTD("\t");
			StartDocument document = eventFactory.createStartDocument();
			writer.add(document);
			writer.add(fin);
			StartElement startElement = eventFactory.createStartElement("", "", raiz);
			writer.add(startElement);
			writer.add(fin);
			StartElement startElement2 = eventFactory.createStartElement("", "", "document");
			writer.add(tab);
			writer.add(startElement2);
			writer.add(fin);

			for (String[] aux : datos) {
				if (!aux[1].equals("-1")) {
					crearNodo(writer, aux[0], aux[1]);
				}
			}

			StartElement startElement3 = eventFactory.createStartElement("", "", "nbestanswers");
			writer.add(tab);
			writer.add(startElement3);
			writer.add(fin);

			for (String auxLista : lista) {
				crearNodo(writer, "answer_item", auxLista);
			}

			writer.add(eventFactory.createEndElement("", "", "nbestanswers"));
			writer.add(fin);

			for (String[] aux2 : datos2) {
				if (!aux2[1].equals("-1")) {
					crearNodo(writer, aux2[0], aux2[1]);
				}
			}

			writer.add(eventFactory.createEndElement("", "", "document"));
			writer.add(fin);
			writer.add(eventFactory.createEndElement("", "", raiz));
			writer.add(fin);
			writer.add(eventFactory.createEndDocument());
			writer.close();

		} catch (XMLStreamException | FileNotFoundException e) {
			// TODO: handle exception
		}
	}

	public static void crearNodo(XMLEventWriter writer, String elemento, String valor) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent fin = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		StartElement startElement = eventFactory.createStartElement("", "", elemento);
		writer.add(tab);
		writer.add(startElement);
		Characters characters = eventFactory.createCharacters(valor);
		writer.add(characters);
		EndElement endElement = eventFactory.createEndElement("", "", elemento);
		writer.add(endElement);
		writer.add(fin);
	}
}
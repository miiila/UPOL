import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlSaveEngine {

    public void saveGame(String fileName, Board board, ArrayList<Player> players, int playerOnTurn) throws XMLStreamException {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();

            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(outputStream);
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("Evade");
            xmlStreamWriter.writeStartElement("History");
            for(Turn turn : board.getHistory()) {
                xmlStreamWriter.writeStartElement("Turn");
                    Position from = turn.getFrom();
                    xmlStreamWriter.writeStartElement("From");
                        xmlStreamWriter.writeStartElement("Row");
                        xmlStreamWriter.writeCharacters(Integer.toString(from.getRow()));
                        xmlStreamWriter.writeEndElement();
                        xmlStreamWriter.writeStartElement("Column");
                        xmlStreamWriter.writeCharacters(Integer.toString(from.getColumn()));
                        xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeEndElement();
                    Position to = turn.getTo();
                    xmlStreamWriter.writeStartElement("To");
                        xmlStreamWriter.writeStartElement("Row");
                        xmlStreamWriter.writeCharacters(Integer.toString(to.getRow()));
                        xmlStreamWriter.writeEndElement();
                        xmlStreamWriter.writeStartElement("Column");
                        xmlStreamWriter.writeCharacters(Integer.toString(to.getColumn()));
                        xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("Players");
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                xmlStreamWriter.writeStartElement("Player");
                xmlStreamWriter.writeAttribute("type", player.getClass().getName());
                    xmlStreamWriter.writeStartElement("Name");
                    xmlStreamWriter.writeCharacters(player.getName());
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeStartElement("OnTurn");
                    xmlStreamWriter.writeCharacters(Boolean.valueOf(i == playerOnTurn).toString());
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeStartElement("Level");
                    xmlStreamWriter.writeCharacters(player.getLevel().toString());
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeStartElement("Sign");
                    xmlStreamWriter.writeCharacters(Integer.toString(player.getSign()));
                    xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();

            outputStream.flush();
            outputStream.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

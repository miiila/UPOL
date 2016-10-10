import java.io.IOException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlSaveEngine {

    public void saveGame(String fileName, Board board, Player[] players, int playerOnTurn) throws XMLStreamException {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();

            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(outputStream);
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("Evade");
            xmlStreamWriter.writeStartElement("Deck");
            String [] rows = board.getDeck().toString().split("#");
            for (int i = 0; i < rows.length; i++) {
                xmlStreamWriter.writeStartElement("Row");
                xmlStreamWriter.writeCharacters(rows[i]);
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();
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
            for (int i = 0; i < players.length; i++) {
                Player player = players[i];
                xmlStreamWriter.writeStartElement("Player");
                if (i == playerOnTurn) { xmlStreamWriter.writeAttribute("onTurn", Boolean.valueOf(true).toString()); }
                    xmlStreamWriter.writeStartElement("Name");
                    xmlStreamWriter.writeCharacters(player.getName());
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeStartElement("Type");
                    xmlStreamWriter.writeCharacters(player.getClass().getName());
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

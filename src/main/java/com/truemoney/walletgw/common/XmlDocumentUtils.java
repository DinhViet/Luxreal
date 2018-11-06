package com.truemoney.walletgw.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlDocumentUtils {
  public static Node getRootElement(Document doc) {
    Node firstChild = doc.getFirstChild();
    while (firstChild != null && firstChild.getNodeType() != Node.ELEMENT_NODE) {
      firstChild = firstChild.getNextSibling();
    }
    return firstChild;
  }

  public static Document readingXMLFromFile(File inFile) {
    DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
    dBF.setIgnoringComments(true); // Ignore the comments present in the
    // XML File when reading the xml
    DocumentBuilder builder = null;
    try {
      builder = dBF.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
    Document doc = null;
    try {
      doc = builder.parse(new FileInputStream(inFile));
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return doc;
  }

  public static void writeXMLToFile(Document document, File outFile) {
    try {
      // create DOMSource for source XML document
      Source xmlSource = new DOMSource(document);
      // create StreamResult for transformation result
      Result result = new StreamResult(new FileOutputStream(outFile));
      // create TransformerFactory
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      // create Transformer for transformation
      Transformer transformer = transformerFactory.newTransformer();

      transformer.setOutputProperty("indent", "yes"); // Java XML Indent
      // transform and deliver content to client
      transformer.transform(xmlSource, result);
      // handle exception creating TransformerFactory
    } catch (TransformerFactoryConfigurationError factoryError) {
      System.err.println("Error creating " + "TransformerFactory");
      factoryError.printStackTrace();
    } catch (TransformerException transformerError) {
      System.err.println("Error transforming document");
      transformerError.printStackTrace();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }

  }

  public static Document parseXmlString(String xmlString) {
    // get the factory
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    try {
      // Using factory get an instance of document builder
      DocumentBuilder db = dbf.newDocumentBuilder();

      // parse using builder to get DOM representation of the XML file
      return db.parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes("utf-8"))));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public static Node getChildElementByName(Element e, String childName) {
    try {
      if (e.hasChildNodes()) {
        NodeList nodes = e.getChildNodes();
        if (nodes != null && nodes.getLength() > 0) {
          for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeName().equalsIgnoreCase(childName)) {
              return node;
            }
          }
        }
      }
    } catch (Exception e2) {
      // TODO: handle exception
    }

    return null;
  }

  public static List<Node> getChildElementsByName(Element e, String childName) {
    List<Node> childNodes = new ArrayList<Node>();
    try {
      if (e.hasChildNodes()) {
        NodeList nodes = e.getChildNodes();
        if (nodes != null && nodes.getLength() > 0) {
          for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeName().equalsIgnoreCase(childName)) {
              childNodes.add(node);
            }
          }
        }
      }
    } catch (Exception e2) {
      // TODO: handle exception
    }

    return childNodes;
  }

  public static String getNodeText(Node node) {
    try {
      if (node.hasChildNodes()) {
        node.normalize();
        Node text = node.getFirstChild();
        if (text != null) {
          switch (text.getNodeType()) {
            case Node.TEXT_NODE:
              return text.getNodeValue();
            case Node.CDATA_SECTION_NODE:
              return ((CDATASection) text).getData();

            default:
              return text.getTextContent();
          }
        }
      }
    } catch (Exception e) {
    }
    return "";
  }

  /*
   * ##################################################### Test
   * ###################################################
   */
  static public void main(String[] arg) {
    boolean validate = false;

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setValidating(validate);
    dbf.setNamespaceAware(true);
    dbf.setIgnoringElementContentWhitespace(true);

    Document doc = null;
    try {
      DocumentBuilder builder = dbf.newDocumentBuilder();
      doc = builder.parse(new InputSource(new StringReader(xmlString)));
    } catch (SAXException e) {
      System.exit(1);
    } catch (ParserConfigurationException e) {
      System.err.println(e);
      System.exit(1);
    } catch (IOException e) {
      System.err.println(e);
      System.exit(1);
    }

    TreeDumper td = new TreeDumper();
    td.dump(doc);
  }

  static String xmlString = "<PHONEBOOK>" + "  <PERSON>" + "   <NAME >Joe Wang</NAME>"
      + "   <EMAIL property=\"working\">joe@yourserver.com</EMAIL>"
      + "   <TELEPHONE>202-999-9999</TELEPHONE>" + "   <WEB>www.java2s.com</WEB>" + "  </PERSON>"
      + "  <PERSON>   " + "<NAME>Karol</NAME>" + "   <EMAIL>karol@yourserver.com</EMAIL>"
      + "   <TELEPHONE>306-999-9999</TELEPHONE>" + "   <WEB>www.java2s.com</WEB>" + "  </PERSON>"
      + "  <PERSON>" + "   <NAME>Green</NAME>" + "   <EMAIL>green@yourserver.com</EMAIL>"
      + "   <TELEPHONE>202-414-9999</TELEPHONE>" + "   <WEB>www.java2s.com</WEB>" + "  </PERSON>"
      + "  </PHONEBOOK>";
}


class TreeDumper {
  public void dump(Document doc) {
    dumpLoop((Node) doc, "");
  }

  private void dumpLoop(Node node, String indent) {
    switch (node.getNodeType()) {
      case Node.CDATA_SECTION_NODE:
        System.out.println(indent + "CDATA_SECTION_NODE");
        break;
      case Node.COMMENT_NODE:
        System.out.println(indent + "COMMENT_NODE");
        break;
      case Node.DOCUMENT_FRAGMENT_NODE:
        System.out.println(indent + "DOCUMENT_FRAGMENT_NODE");
        break;
      case Node.DOCUMENT_NODE:
        System.out.println(indent + "DOCUMENT_NODE");
        break;
      case Node.DOCUMENT_TYPE_NODE:
        System.out.println(indent + "DOCUMENT_TYPE_NODE");
        break;
      case Node.ELEMENT_NODE:
        System.out.println(indent + "ELEMENT_NODE");
        break;
      case Node.ENTITY_NODE:
        System.out.println(indent + "ENTITY_NODE");
        break;
      case Node.ENTITY_REFERENCE_NODE:
        System.out.println(indent + "ENTITY_REFERENCE_NODE");
        break;
      case Node.NOTATION_NODE:
        System.out.println(indent + "NOTATION_NODE");
        break;
      case Node.PROCESSING_INSTRUCTION_NODE:
        System.out.println(indent + "PROCESSING_INSTRUCTION_NODE");
        break;
      case Node.TEXT_NODE:
        System.out.println(indent + "TEXT_NODE");
        break;
      default:
        System.out.println(indent + "Unknown node");
        break;
    }
    NodeList list = node.getChildNodes();
    for (int i = 0; i < list.getLength(); i++)
      dumpLoop(list.item(i), indent + "   ");
  }
}

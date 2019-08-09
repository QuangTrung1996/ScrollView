package com.trung.example.demoapp.xml;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseXml
{
    public static List<ParsedDataSet> parseXmlAsync(Context context, String s)
    {
        try
        {
            List<ParsedDataSet> a = new parseXmlAsync().execute().get();

            if (a != null)
            {
                Log.d("ParseXml", "parseXmlAsync: " + a.size());
            }
            else
            {
                Log.d("ParseXml", "parseXmlAsync: null");
            }
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private static class parseXmlAsync extends AsyncTask<String, String, List<ParsedDataSet>>
    {
        @Override
        protected List<ParsedDataSet> doInBackground(String... strings)
        {
            List<ParsedDataSet> parsedDataSet = null;

            try
            {
                // initialize our input source variable
                InputSource inputSource = null;

                // XML from sdcard
                // make sure sample.xml is in your root SD card directory
                File xmlFile = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS) + "/sample.xml");

                if (xmlFile.exists() == false)
                {
                    return parsedDataSet;
                }

                FileInputStream xmlFileInputStream = new FileInputStream(xmlFile);
                inputSource = new InputSource(xmlFileInputStream);

                // instantiate SAX parser
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();

                // get the XML reader
                XMLReader xmlReader = saxParser.getXMLReader();

                // prepare and set the XML content or data handler before
                // parsing
                XmlContentHandler xmlContentHandler = new XmlContentHandler();
                xmlReader.setContentHandler(xmlContentHandler);

                // parse the XML input source
                xmlReader.parse(inputSource);

                // put the parsed data to a List
                parsedDataSet = xmlContentHandler.getParsedData();
            } catch (NullPointerException e)
            {
                parsedDataSet = null;
                e.printStackTrace();
            }
            catch (Exception e)
            {
                parsedDataSet = null;
                e.printStackTrace();
            }

            return parsedDataSet;
        }

        @Override
        protected void onPostExecute(List<ParsedDataSet> a)
        {
            // your do stuff after parsing the XML
        }
    }
}

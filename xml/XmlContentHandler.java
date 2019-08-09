package com.trung.example.demoapp.xml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XmlContentHandler extends DefaultHandler
{
    private StringBuilder mStringBuilder = new StringBuilder();
    private ParsedDataSet mParsedDataSet = new ParsedDataSet();
    private List<ParsedDataSet> mParsedDataSetList = new ArrayList<>();

    public List<ParsedDataSet> getParsedData()
    {
        return mParsedDataSetList;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
    {
        if (localName.equals("Owner"))
        {
            // meaning new data object will be made
            mParsedDataSet = new ParsedDataSet();
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName)
    {
        // Owners
        if (localName.equals("Owner"))
        {
            this.mParsedDataSetList.add(mParsedDataSet);
            mParsedDataSet.parentTag = "Owners";
        }
        else if (localName.equals("Name"))
        {
            mParsedDataSet.name = mStringBuilder.toString().trim();
        }

        else if (localName.equals("Age"))
        {
            mParsedDataSet.age = mStringBuilder.toString().trim();
        }

        else if (localName.equals("EmailAddress")) {
            mParsedDataSet.emailAddress = mStringBuilder.toString().trim();
        }

        // empty our string builder
        mStringBuilder.setLength(0);
    }

    @Override
    public void characters(char ch[], int start, int length)
    {
        // append the value to our string builder
        mStringBuilder.append(ch, start, length);
    }
}
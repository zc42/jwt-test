package com.zc.tests.jwt.services;

import java.io.FileInputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;

public class Mp3Parse {

    public static void main(final String[] args) throws Exception {

        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        String filePath = "./mp3/valid-sample-with-required-tags.mp3";
        FileInputStream inputstream = new FileInputStream(filePath);
        ParseContext context = new ParseContext();

        //Mp3 parser
        Mp3Parser  Mp3Parser = new  Mp3Parser();
        Mp3Parser.parse(inputstream, handler, metadata, context);
        LyricsHandler lyrics = new LyricsHandler(inputstream,handler);

        while(lyrics.hasLyrics()) {
            System.out.println(lyrics);
        }

        System.out.println("Contents of the document:" + handler);
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();

        for(String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
}

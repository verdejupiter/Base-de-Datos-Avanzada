/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conexong;

/**
 *
 * @author Usuario
 */
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConexONG {
    public static void main(String[] args) {
        // Configurar la conexión a MongoDB
        String connectionString = "mongodb+srv://ONG:12345@ong.nskhjqm.mongodb.net/?retryWrites=true&w=majority";
        MongoClientURI uri = new MongoClientURI(connectionString);

        // Conectar a MongoDB y obtener la base de datos
        try (MongoClient mongoClient = new MongoClient(uri)) {
            MongoDatabase database = mongoClient.getDatabase("OngHiloRojo");

            // Acceder a la colección en la base de datos
            MongoCollection<Document>collection = database.getCollection("usuarios");
            
            FindIterable<Document> documents = collection.find();
            MongoCursor<Document> cursor = documents.iterator();

            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document.toJson());
            }       
        }
    }
}
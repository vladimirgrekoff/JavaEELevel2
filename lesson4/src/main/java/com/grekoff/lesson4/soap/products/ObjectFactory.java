//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.02.20 at 04:23:07 AM NOVT 
//


package com.grekoff.lesson4.soap.products;

import jakarta.xml.bind.annotation.XmlRegistry;

//import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.grekoff.lesson4.products package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.grekoff.lesson4.products
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductByTitleRequest }
     * 
     */
    public GetProductByTitleRequest createGetProductByTitleRequest() {
        return new GetProductByTitleRequest();
    }

    /**
     * Create an instance of {@link GetAllProductsRequest }
     * 
     */
    public GetAllProductsRequest createGetAllProductsRequest() {
        return new GetAllProductsRequest();
    }

    /**
     * Create an instance of {@link GetProductByTitleResponse }
     * 
     */
    public GetProductByTitleResponse createGetProductByTitleResponse() {
        return new GetProductByTitleResponse();
    }

    /**
     * Create an instance of {@link ProductSoap }
     * 
     */
    public ProductSoap createProductSoap() {
        return new ProductSoap();
    }

    /**
     * Create an instance of {@link GetAllProductsResponse }
     * 
     */
    public GetAllProductsResponse createGetAllProductsResponse() {
        return new GetAllProductsResponse();
    }

}

-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2019 at 04:35 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hstestdb`
--
CREATE DATABASE IF NOT EXISTS `hstestdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `hstestdb`;

-- --------------------------------------------------------

--
-- Table structure for table `html`
--

CREATE TABLE `html` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `html`
--

INSERT INTO `html` (`uuid`, `content`) VALUES
('3aff2f9c-0c7f-4630-99ad-27a0cf1af137', '<html><body>This is the html page 3aff2f9c-0c7f-4630-99ad-27a0cf1af137.</body></html>'),
('6df1047e-cf19-4a83-8cf3-38f5e53f7725', '<html><body>This is the html page 6df1047e-cf19-4a83-8cf3-38f5e53f7725.</body></html>'),
('77ec1d68-84e1-40f4-be8e-066e02f4e373', '<html><body>This is the html page 77ec1d68-84e1-40f4-be8e-066e02f4e373.</body></html>'),
('79e01232-5ea4-41c8-9331-1c1880a1d3c2', '<html><body>This is the html page 79e01232-5ea4-41c8-9331-1c1880a1d3c2.</body></html>'),
('a35b6c5e-22d6-4707-98b4-462482e26c9e', '<html><body>This is the html page a35b6c5e-22d6-4707-98b4-462482e26c9e.</body></html>');

-- --------------------------------------------------------

--
-- Table structure for table `xml`
--

CREATE TABLE `xml` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xml`
--

INSERT INTO `xml` (`uuid`, `content`) VALUES
('ddcab7d0-636c-11e4-8db3-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <people xmlns=\"http://www.esei.uvigo.es/dai/proyecto\"      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"     xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample.xsd\">  <person dni=\"123456\"><name>Pepe</name></person> </people>'),
('ea118888-6908-11e4-9620-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <tns:collection xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample2.xsd \">  <disc year=\"1972\">   <name>Live in Japan</name>   <genre>Hard Rock</genre>   <artist>Deep Purple</artist>  </disc>  <disc year=\"1972\">   <name>Led Zeppelin</name>   <genre>Hard Rock</genre>   <artist>Led Zeppelin</artist>  </disc>  <disc year=\"1990\">   <name>Shake Your Money Maker</name>   <genre>Southern Rock</genre>   <artist>The Black Crowes</artist>  </disc>    <movie year=\"1972\">   <name>The Godfather</name>   <genre>Drama</genre>   <director>Francis Ford Coppola</director>  </movie>  <movie>   <name>Interstellar</name>   <genre>Adventure</genre>   <director>Christopher Nolan</director>  </movie>    <book year=\"1965\">   <name>Dune</name>   <genre>Science Fiction</genre>   <author>Frank Herbert</author>   <pages>412</pages>  </book>  <book year=\"1922\">   <name>Siddhartha</name>   <genre>Novel</genre>   <author>Hermann Hesse</author>   <pages>152</pages>  </book> </tns:collection>');

-- --------------------------------------------------------

--
-- Table structure for table `xsd`
--

CREATE TABLE `xsd` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xsd`
--

INSERT INTO `xsd` (`uuid`, `content`) VALUES
('05b88faa-6909-11e4-aadc-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\"   targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\">  <element name=\"collection\">   <complexType>    <sequence>     <element name=\"disc\" type=\"tns:disc\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"movie\" type=\"tns:movie\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"book\" type=\"tns:book\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>    </sequence>   </complexType>  </element>      <complexType name=\"item\">      <sequence>       <element name=\"name\" type=\"string\"/>      </sequence>      <attribute name=\"year\" type=\"unsignedInt\"/>     </complexType>      <complexType name=\"disc\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"artist\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"movie\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"director\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"book\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"author\" type=\"string\"/>         <element name=\"pages\" type=\"unsignedInt\"/>        </sequence>       </extension>      </complexContent>     </complexType> </schema>'),
('e5b64c34-636c-11e4-b729-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\" xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" elementFormDefault=\"qualified\">  <element name=\"people\">   <complexType>    <sequence>     <element name=\"person\">      <complexType>       <sequence>        <element name=\"name\" type=\"string\" minOccurs=\"1\" maxOccurs=\"1\"/>       </sequence>       <attribute name=\"dni\" type=\"string\"/>      </complexType>     </element>    </sequence>   </complexType>  </element> </schema>');

-- --------------------------------------------------------

--
-- Table structure for table `xslt`
--

CREATE TABLE `xslt` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL,
  `xsd` char(36) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xslt`
--

INSERT INTO `xslt` (`uuid`, `content`, `xsd`) VALUES
('f260dfee-636c-11e4-bbdd-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>People</title>    </head>    <body>     <div>      <h1>People</h1>      <xsl:apply-templates select=\"tns:people/tns:person\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"tns:person\">   <div class=\"person\">    <h3>DNI: <xsl:value-of select=\"@dni\"/></h3>    <div class=\"name\"><xsl:value-of select=\"tns:name\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', 'e5b64c34-636c-11e4-b729-685b35c84fb4'),
('1fd26c94-6909-11e4-9a75-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>Collection</title>    </head>    <body>     <div>      <h1>Collection</h1>      <h2>Discs</h2>      <xsl:apply-templates select=\"tns:collection/disc\" />      <hr/>      <h2>Movies</h2>      <xsl:apply-templates select=\"tns:collection/movie\" />      <hr/>      <h2>Books</h2>      <xsl:apply-templates select=\"tns:collection/book\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"disc\">   <div class=\"disc\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"artist\">Artist: <xsl:value-of select=\"artist\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"movie\">   <div class=\"movie\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"director\">Director: <xsl:value-of select=\"director\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"book\">   <div class=\"book\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"author\">Author: <xsl:value-of select=\"author\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>    <div class=\"pages\">Pages: <xsl:value-of select=\"pages\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', '05b88faa-6909-11e4-aadc-685b35c84fb4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `html`
--
ALTER TABLE `html`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xml`
--
ALTER TABLE `xml`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xsd`
--
ALTER TABLE `xsd`
  ADD PRIMARY KEY (`uuid`);
--
-- Database: `hstestdb1`
--
CREATE DATABASE IF NOT EXISTS `hstestdb1` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `hstestdb1`;

-- --------------------------------------------------------

--
-- Table structure for table `html`
--

CREATE TABLE `html` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `html`
--

INSERT INTO `html` (`uuid`, `content`) VALUES
('3aff2f9c-0c7f-1111-99ad-27a0cf1af137', '<html><body>This is the html page 3aff2f9c-0c7f-1111-99ad-27a0cf1af137.</body></html>'),
('6df1047e-cf19-1111-8cf3-38f5e53f7725', '<html><body>This is the html page 6df1047e-cf19-1111-8cf3-38f5e53f7725.</body></html>'),
('77ec1d68-84e1-1111-be8e-066e02f4e373', '<html><body>This is the html page 77ec1d68-84e1-1111-be8e-066e02f4e373.</body></html>'),
('79e01232-5ea4-1111-9331-1c1880a1d3c2', '<html><body>This is the html page 79e01232-5ea4-1111-9331-1c1880a1d3c2.</body></html>'),
('a35b6c5e-22d6-1111-98b4-462482e26c9e', '<html><body>This is the html page a35b6c5e-22d6-1111-98b4-462482e26c9e.</body></html>');

-- --------------------------------------------------------

--
-- Table structure for table `xml`
--

CREATE TABLE `xml` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xml`
--

INSERT INTO `xml` (`uuid`, `content`) VALUES
('ddcab7d0-636c-1111-8db3-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <people xmlns=\"http://www.esei.uvigo.es/dai/proyecto\"      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"     xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample.xsd\">  <person1 dni=\"11111111A\"><name>Pepe</name></person1> </people>'),
('ea118888-6908-1111-9620-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <tns:collection xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample2.xsd \">  <disc1 year=\"1972\">   <name>Live in Japan</name>   <genre>Hard Rock</genre>   <artist>Deep Purple</artist>  </disc1>  <disc1 year=\"1972\">   <name>Led Zeppelin</name>   <genre>Hard Rock</genre>   <artist>Led Zeppelin</artist>  </disc1>  <disc1 year=\"1990\">   <name>Shake Your Money Maker</name>   <genre>Southern Rock</genre>   <artist>The Black Crowes</artist>  </disc1>    <movie1 year=\"1972\">   <name>The Godfather</name>   <genre>Drama</genre>   <director>Francis Ford Coppola</director>  </movie1>  <movie1>   <name>Interstellar</name>   <genre>Adventure</genre>   <director>Christopher Nolan</director>  </movie1>    <book1 year=\"1965\">   <name>Dune</name>   <genre>Science Fiction</genre>   <author>Frank Herbert</author>   <pages>412</pages>  </book1>  <book1 year=\"1922\">   <name>Siddhartha</name>   <genre>Novel</genre>   <author>Hermann Hesse</author>   <pages>152</pages>  </book1> </tns:collection>');

-- --------------------------------------------------------

--
-- Table structure for table `xsd`
--

CREATE TABLE `xsd` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xsd`
--

INSERT INTO `xsd` (`uuid`, `content`) VALUES
('05b88faa-6909-1111-4444-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\"   targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\">  <element name=\"collection\">   <complexType>    <sequence>     <element name=\"disc4\" type=\"tns:disc4\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"movie4\" type=\"tns:movie4\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"book4\" type=\"tns:book4\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>    </sequence>   </complexType>  </element>      <complexType name=\"item\">      <sequence>       <element name=\"name\" type=\"string\"/>      </sequence>      <attribute name=\"year\" type=\"unsignedInt\"/>     </complexType>      <complexType name=\"disc4\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"artist\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"movie4\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"director\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"book4\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"author\" type=\"string\"/>         <element name=\"pages\" type=\"unsignedInt\"/>        </sequence>       </extension>      </complexContent>     </complexType> </schema>'),
('05b88faa-6909-1111-aadc-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\"   targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\">  <element name=\"collection\">   <complexType>    <sequence>     <element name=\"disc1\" type=\"tns:disc1\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"movie1\" type=\"tns:movie1\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"book1\" type=\"tns:book1\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>    </sequence>   </complexType>  </element>      <complexType name=\"item\">      <sequence>       <element name=\"name\" type=\"string\"/>      </sequence>      <attribute name=\"year\" type=\"unsignedInt\"/>     </complexType>      <complexType name=\"disc1\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"artist\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"movie1\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"director\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"book1\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"author\" type=\"string\"/>         <element name=\"pages\" type=\"unsignedInt\"/>        </sequence>       </extension>      </complexContent>     </complexType> </schema>'),
('e5b64c34-636c-1111-b729-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\" xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" elementFormDefault=\"qualified\">  <element name=\"people\">   <complexType>    <sequence>     <element name=\"person1\">      <complexType>       <sequence>        <element name=\"name\" type=\"string\" minOccurs=\"1\" maxOccurs=\"1\"/>       </sequence>       <attribute name=\"dni\" type=\"string\"/>      </complexType>     </element>    </sequence>   </complexType>  </element> </schema>');

-- --------------------------------------------------------

--
-- Table structure for table `xslt`
--

CREATE TABLE `xslt` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL,
  `xsd` char(36) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xslt`
--

INSERT INTO `xslt` (`uuid`, `content`, `xsd`) VALUES
('f260dfee-636c-1111-bbdd-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>People</title>    </head>    <body>     <div>      <h1>People</h1>      <xsl:apply-templates select=\"tns:people/tns:person1\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"tns:person1\">   <div class=\"person1\">    <h3>DNI: <xsl:value-of select=\"@dni\"/></h3>    <div class=\"name\"><xsl:value-of select=\"tns:name\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', 'e5b64c34-636c-1111-b729-685b35c84fb4'),
('1fd26c94-6909-1111-9a75-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>Collection</title>    </head>    <body>     <div>      <h1>Collection</h1>      <h2>Discs 1</h2>      <xsl:apply-templates select=\"tns:collection/disc1\" />      <hr/>      <h2>Movies 1</h2>      <xsl:apply-templates select=\"tns:collection/movie1\" />      <hr/>      <h2>Books 1</h2>      <xsl:apply-templates select=\"tns:collection/book1\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"disc1\">   <div class=\"disc1\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"artist\">Artist: <xsl:value-of select=\"artist\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"movie1\">   <div class=\"movie1\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"director\">Director: <xsl:value-of select=\"director\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"book1\">   <div class=\"book1\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"author\">Author: <xsl:value-of select=\"author\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>    <div class=\"pages\">Pages: <xsl:value-of select=\"pages\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', '05b88faa-6909-1111-aadc-685b35c84fb4'),
('1fd26c94-6909-1111-4444-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>Collection</title>    </head>    <body>     <div>      <h1>Collection</h1>      <h2>Discs 4</h2>      <xsl:apply-templates select=\"tns:collection/disc4\" />      <hr/>      <h2>Movies 4</h2>      <xsl:apply-templates select=\"tns:collection/movie4\" />      <hr/>      <h2>Books 4</h2>      <xsl:apply-templates select=\"tns:collection/book4\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"disc4\">   <div class=\"disc4\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"artist\">Artist: <xsl:value-of select=\"artist\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"movie4\">   <div class=\"movie4\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"director\">Director: <xsl:value-of select=\"director\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"book4\">   <div class=\"book4\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"author\">Author: <xsl:value-of select=\"author\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>    <div class=\"pages\">Pages: <xsl:value-of select=\"pages\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', '05b88faa-6909-1111-4444-685b35c84fb4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `html`
--
ALTER TABLE `html`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xml`
--
ALTER TABLE `xml`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xsd`
--
ALTER TABLE `xsd`
  ADD PRIMARY KEY (`uuid`);
--
-- Database: `hstestdb2`
--
CREATE DATABASE IF NOT EXISTS `hstestdb2` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `hstestdb2`;

-- --------------------------------------------------------

--
-- Table structure for table `html`
--

CREATE TABLE `html` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `html`
--

INSERT INTO `html` (`uuid`, `content`) VALUES
('3aff2f9c-0c7f-2222-99ad-27a0cf1af137', '<html><body>This is the html page 3aff2f9c-0c7f-2222-99ad-27a0cf1af137.</body></html>'),
('6df1047e-cf19-2222-8cf3-38f5e53f7725', '<html><body>This is the html page 6df1047e-cf19-2222-8cf3-38f5e53f7725.</body></html>'),
('77ec1d68-84e1-2222-be8e-066e02f4e373', '<html><body>This is the html page 77ec1d68-84e1-2222-be8e-066e02f4e373.</body></html>'),
('79e01232-5ea4-2222-9331-1c1880a1d3c2', '<html><body>This is the html page 79e01232-5ea4-2222-9331-1c1880a1d3c2.</body></html>'),
('a35b6c5e-22d6-2222-98b4-462482e26c9e', '<html><body>This is the html page a35b6c5e-22d6-2222-98b4-462482e26c9e.</body></html>');

-- --------------------------------------------------------

--
-- Table structure for table `xml`
--

CREATE TABLE `xml` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xml`
--

INSERT INTO `xml` (`uuid`, `content`) VALUES
('ddcab7d0-636c-2222-8db3-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <people xmlns=\"http://www.esei.uvigo.es/dai/proyecto\"      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"     xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample.xsd\">  <person2 dni=\"22222222B\"><name>Pepe</name></person2> </people>'),
('ea118888-6908-2222-9620-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <tns:collection xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample2.xsd \">  <disc2 year=\"1972\">   <name>Live in Japan</name>   <genre>Hard Rock</genre>   <artist>Deep Purple</artist>  </disc2>  <disc2 year=\"1972\">   <name>Led Zeppelin</name>   <genre>Hard Rock</genre>   <artist>Led Zeppelin</artist>  </disc2>  <disc2 year=\"1990\">   <name>Shake Your Money Maker</name>   <genre>Southern Rock</genre>   <artist>The Black Crowes</artist>  </disc2>    <movie2 year=\"1972\">   <name>The Godfather</name>   <genre>Drama</genre>   <director>Francis Ford Coppola</director>  </movie2>  <movie2>   <name>Interstellar</name>   <genre>Adventure</genre>   <director>Christopher Nolan</director>  </movie2>    <book2 year=\"1965\">   <name>Dune</name>   <genre>Science Fiction</genre>   <author>Frank Herbert</author>   <pages>412</pages>  </book2>  <book2 year=\"1922\">   <name>Siddhartha</name>   <genre>Novel</genre>   <author>Hermann Hesse</author>   <pages>152</pages>  </book2> </tns:collection>');

-- --------------------------------------------------------

--
-- Table structure for table `xsd`
--

CREATE TABLE `xsd` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xsd`
--

INSERT INTO `xsd` (`uuid`, `content`) VALUES
('05b88faa-6909-2222-3333-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\"   targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\">  <element name=\"collection\">   <complexType>    <sequence>     <element name=\"disc3\" type=\"tns:disc3\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"movie3\" type=\"tns:movie3\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"book3\" type=\"tns:book3\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>    </sequence>   </complexType>  </element>      <complexType name=\"item\">      <sequence>       <element name=\"name\" type=\"string\"/>      </sequence>      <attribute name=\"year\" type=\"unsignedInt\"/>     </complexType>      <complexType name=\"disc3\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"artist\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"movie3\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"director\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"book3\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"author\" type=\"string\"/>         <element name=\"pages\" type=\"unsignedInt\"/>        </sequence>       </extension>      </complexContent>     </complexType> </schema>'),
('05b88faa-6909-2222-aadc-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\"   targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\">  <element name=\"collection\">   <complexType>    <sequence>     <element name=\"disc2\" type=\"tns:disc2\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"movie2\" type=\"tns:movie2\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"book2\" type=\"tns:book2\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>    </sequence>   </complexType>  </element>      <complexType name=\"item\">      <sequence>       <element name=\"name\" type=\"string\"/>      </sequence>      <attribute name=\"year\" type=\"unsignedInt\"/>     </complexType>      <complexType name=\"disc2\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"artist\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"movie2\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"director\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"book2\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"author\" type=\"string\"/>         <element name=\"pages\" type=\"unsignedInt\"/>        </sequence>       </extension>      </complexContent>     </complexType> </schema>'),
('e5b64c34-636c-2222-b729-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\" xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" elementFormDefault=\"qualified\">  <element name=\"people\">   <complexType>    <sequence>     <element name=\"person2\">      <complexType>       <sequence>        <element name=\"name\" type=\"string\" minOccurs=\"1\" maxOccurs=\"1\"/>       </sequence>       <attribute name=\"dni\" type=\"string\"/>      </complexType>     </element>    </sequence>   </complexType>  </element> </schema>');

-- --------------------------------------------------------

--
-- Table structure for table `xslt`
--

CREATE TABLE `xslt` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL,
  `xsd` char(36) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xslt`
--

INSERT INTO `xslt` (`uuid`, `content`, `xsd`) VALUES
('f260dfee-636c-2222-bbdd-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>People</title>    </head>    <body>     <div>      <h1>People</h1>      <xsl:apply-templates select=\"tns:people/tns:person2\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"tns:person2\">   <div class=\"person2\">    <h3>DNI: <xsl:value-of select=\"@dni\"/></h3>    <div class=\"name\"><xsl:value-of select=\"tns:name\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', 'e5b64c34-636c-2222-b729-685b35c84fb4'),
('1fd26c94-6909-2222-9a75-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>Collection</title>    </head>    <body>     <div>      <h1>Collection</h1>      <h2>Discs 2</h2>      <xsl:apply-templates select=\"tns:collection/disc2\" />      <hr/>      <h2>Movies 2</h2>      <xsl:apply-templates select=\"tns:collection/movie2\" />      <hr/>      <h2>Books 2</h2>      <xsl:apply-templates select=\"tns:collection/book2\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"disc2\">   <div class=\"disc2\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"artist\">Artist: <xsl:value-of select=\"artist\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"movie2\">   <div class=\"movie2\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"director\">Director: <xsl:value-of select=\"director\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"book2\">   <div class=\"book2\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"author\">Author: <xsl:value-of select=\"author\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>    <div class=\"pages\">Pages: <xsl:value-of select=\"pages\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', '05b88faa-6909-2222-aadc-685b35c84fb4'),
('1fd26c94-6909-2222-3333-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>Collection</title>    </head>    <body>     <div>      <h1>Collection</h1>      <h2>Discs 3</h2>      <xsl:apply-templates select=\"tns:collection/disc3\" />      <hr/>      <h2>Movies 3</h2>      <xsl:apply-templates select=\"tns:collection/movie3\" />      <hr/>      <h2>Books 3</h2>      <xsl:apply-templates select=\"tns:collection/book3\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"disc3\">   <div class=\"disc3\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"artist\">Artist: <xsl:value-of select=\"artist\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"movie3\">   <div class=\"movie3\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"director\">Director: <xsl:value-of select=\"director\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"book3\">   <div class=\"book3\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"author\">Author: <xsl:value-of select=\"author\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>    <div class=\"pages\">Pages: <xsl:value-of select=\"pages\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', '05b88faa-6909-2222-3333-685b35c84fb4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `html`
--
ALTER TABLE `html`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xml`
--
ALTER TABLE `xml`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xsd`
--
ALTER TABLE `xsd`
  ADD PRIMARY KEY (`uuid`);
--
-- Database: `hstestdb3`
--
CREATE DATABASE IF NOT EXISTS `hstestdb3` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `hstestdb3`;

-- --------------------------------------------------------

--
-- Table structure for table `html`
--

CREATE TABLE `html` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `html`
--

INSERT INTO `html` (`uuid`, `content`) VALUES
('3aff2f9c-0c7f-3333-99ad-27a0cf1af137', '<html><body>This is the html page 3aff2f9c-0c7f-3333-99ad-27a0cf1af137.</body></html>'),
('6df1047e-cf19-3333-8cf3-38f5e53f7725', '<html><body>This is the html page 6df1047e-cf19-3333-8cf3-38f5e53f7725.</body></html>'),
('77ec1d68-84e1-3333-be8e-066e02f4e373', '<html><body>This is the html page 77ec1d68-84e1-3333-be8e-066e02f4e373.</body></html>'),
('79e01232-5ea4-3333-9331-1c1880a1d3c2', '<html><body>This is the html page 79e01232-5ea4-3333-9331-1c1880a1d3c2.</body></html>'),
('a35b6c5e-22d6-3333-98b4-462482e26c9e', '<html><body>This is the html page a35b6c5e-22d6-3333-98b4-462482e26c9e.</body></html>');

-- --------------------------------------------------------

--
-- Table structure for table `xml`
--

CREATE TABLE `xml` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xml`
--

INSERT INTO `xml` (`uuid`, `content`) VALUES
('ddcab7d0-636c-3333-8db3-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <people xmlns=\"http://www.esei.uvigo.es/dai/proyecto\"      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"     xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample.xsd\">  <person3 dni=\"33333333C\"><name>Pepe</name></person3> </people>'),
('ea118888-6908-3333-9620-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <tns:collection xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample2.xsd \">  <disc3 year=\"1972\">   <name>Live in Japan</name>   <genre>Hard Rock</genre>   <artist>Deep Purple</artist>  </disc3>  <disc3 year=\"1972\">   <name>Led Zeppelin</name>   <genre>Hard Rock</genre>   <artist>Led Zeppelin</artist>  </disc3>  <disc3 year=\"1990\">   <name>Shake Your Money Maker</name>   <genre>Southern Rock</genre>   <artist>The Black Crowes</artist>  </disc3>    <movie3 year=\"1972\">   <name>The Godfather</name>   <genre>Drama</genre>   <director>Francis Ford Coppola</director>  </movie3>  <movie3>   <name>Interstellar</name>   <genre>Adventure</genre>   <director>Christopher Nolan</director>  </movie3>    <book3 year=\"1965\">   <name>Dune</name>   <genre>Science Fiction</genre>   <author>Frank Herbert</author>   <pages>412</pages>  </book3>  <book3 year=\"1922\">   <name>Siddhartha</name>   <genre>Novel</genre>   <author>Hermann Hesse</author>   <pages>152</pages>  </book3> </tns:collection>');

-- --------------------------------------------------------

--
-- Table structure for table `xsd`
--

CREATE TABLE `xsd` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xsd`
--

INSERT INTO `xsd` (`uuid`, `content`) VALUES
('05b88faa-6909-3333-aadc-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\"   targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\">  <element name=\"collection\">   <complexType>    <sequence>     <element name=\"disc3\" type=\"tns:disc3\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"movie3\" type=\"tns:movie3\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"book3\" type=\"tns:book3\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>    </sequence>   </complexType>  </element>      <complexType name=\"item\">      <sequence>       <element name=\"name\" type=\"string\"/>      </sequence>      <attribute name=\"year\" type=\"unsignedInt\"/>     </complexType>      <complexType name=\"disc3\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"artist\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"movie3\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"director\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"book3\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"author\" type=\"string\"/>         <element name=\"pages\" type=\"unsignedInt\"/>        </sequence>       </extension>      </complexContent>     </complexType> </schema>'),
('e5b64c34-636c-3333-1111-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\" xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" elementFormDefault=\"qualified\">  <element name=\"people\">   <complexType>    <sequence>     <element name=\"person1\">      <complexType>       <sequence>        <element name=\"name\" type=\"string\" minOccurs=\"1\" maxOccurs=\"1\"/>       </sequence>       <attribute name=\"dni\" type=\"string\"/>      </complexType>     </element>    </sequence>   </complexType>  </element> </schema>'),
('e5b64c34-636c-3333-b729-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\" xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" elementFormDefault=\"qualified\">  <element name=\"people\">   <complexType>    <sequence>     <element name=\"person3\">      <complexType>       <sequence>        <element name=\"name\" type=\"string\" minOccurs=\"1\" maxOccurs=\"1\"/>       </sequence>       <attribute name=\"dni\" type=\"string\"/>      </complexType>     </element>    </sequence>   </complexType>  </element> </schema>');

-- --------------------------------------------------------

--
-- Table structure for table `xslt`
--

CREATE TABLE `xslt` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL,
  `xsd` char(36) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xslt`
--

INSERT INTO `xslt` (`uuid`, `content`, `xsd`) VALUES
('f260dfee-636c-3333-bbdd-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>People</title>    </head>    <body>     <div>      <h1>People</h1>      <xsl:apply-templates select=\"tns:people/tns:person3\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"tns:person3\">   <div class=\"person3\">    <h3>DNI: <xsl:value-of select=\"@dni\"/></h3>    <div class=\"name\"><xsl:value-of select=\"tns:name\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', 'e5b64c34-636c-3333-b729-685b35c84fb4'),
('f260dfee-636c-3333-1111-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>People</title>    </head>    <body>     <div>      <h1>People</h1>      <xsl:apply-templates select=\"tns:people/tns:person1\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"tns:person1\">   <div class=\"person1\">    <h3>DNI: <xsl:value-of select=\"@dni\"/></h3>    <div class=\"name\"><xsl:value-of select=\"tns:name\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', 'e5b64c34-636c-3333-1111-685b35c84fb4'),
('1fd26c94-6909-3333-9a75-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>Collection</title>    </head>    <body>     <div>      <h1>Collection</h1>      <h2>Discs 3</h2>      <xsl:apply-templates select=\"tns:collection/disc3\" />      <hr/>      <h2>Movies 3</h2>      <xsl:apply-templates select=\"tns:collection/movie3\" />      <hr/>      <h2>Books 3</h2>      <xsl:apply-templates select=\"tns:collection/book3\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"disc3\">   <div class=\"disc3\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"artist\">Artist: <xsl:value-of select=\"artist\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"movie3\">   <div class=\"movie3\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"director\">Director: <xsl:value-of select=\"director\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"book3\">   <div class=\"book3\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"author\">Author: <xsl:value-of select=\"author\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>    <div class=\"pages\">Pages: <xsl:value-of select=\"pages\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', '05b88faa-6909-3333-aadc-685b35c84fb4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `html`
--
ALTER TABLE `html`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xml`
--
ALTER TABLE `xml`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xsd`
--
ALTER TABLE `xsd`
  ADD PRIMARY KEY (`uuid`);
--
-- Database: `hstestdb4`
--
CREATE DATABASE IF NOT EXISTS `hstestdb4` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `hstestdb4`;

-- --------------------------------------------------------

--
-- Table structure for table `html`
--

CREATE TABLE `html` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `html`
--

INSERT INTO `html` (`uuid`, `content`) VALUES
('3aff2f9c-0c7f-4444-99ad-27a0cf1af137', '<html><body>This is the html page 3aff2f9c-0c7f-4444-99ad-27a0cf1af137.</body></html>'),
('6df1047e-cf19-4444-8cf3-38f5e53f7725', '<html><body>This is the html page 6df1047e-cf19-4444-8cf3-38f5e53f7725.</body></html>'),
('77ec1d68-84e1-4444-be8e-066e02f4e373', '<html><body>This is the html page 77ec1d68-84e1-4444-be8e-066e02f4e373.</body></html>'),
('79e01232-5ea4-4444-9331-1c1880a1d3c2', '<html><body>This is the html page 79e01232-5ea4-4444-9331-1c1880a1d3c2.</body></html>'),
('a35b6c5e-22d6-4444-98b4-462482e26c9e', '<html><body>This is the html page a35b6c5e-22d6-4444-98b4-462482e26c9e.</body></html>');

-- --------------------------------------------------------

--
-- Table structure for table `xml`
--

CREATE TABLE `xml` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xml`
--

INSERT INTO `xml` (`uuid`, `content`) VALUES
('ddcab7d0-636c-4444-8db3-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <people xmlns=\"http://www.esei.uvigo.es/dai/proyecto\"      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"     xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample.xsd\">  <person4 dni=\"44444444D\"><name>Pepe</name></person4> </people>'),
('ea118888-6908-4444-9620-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>  <tns:collection xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.esei.uvigo.es/dai/proyecto sample2.xsd \">  <disc4 year=\"1972\">   <name>Live in Japan</name>   <genre>Hard Rock</genre>   <artist>Deep Purple</artist>  </disc4>  <disc4 year=\"1972\">   <name>Led Zeppelin</name>   <genre>Hard Rock</genre>   <artist>Led Zeppelin</artist>  </disc4>  <disc4 year=\"1990\">   <name>Shake Your Money Maker</name>   <genre>Southern Rock</genre>   <artist>The Black Crowes</artist>  </disc4>    <movie4 year=\"1972\">   <name>The Godfather</name>   <genre>Drama</genre>   <director>Francis Ford Coppola</director>  </movie4>  <movie4>   <name>Interstellar</name>   <genre>Adventure</genre>   <director>Christopher Nolan</director>  </movie4>    <book4 year=\"1965\">   <name>Dune</name>   <genre>Science Fiction</genre>   <author>Frank Herbert</author>   <pages>412</pages>  </book4>  <book4 year=\"1922\">   <name>Siddhartha</name>   <genre>Novel</genre>   <author>Hermann Hesse</author>   <pages>152</pages>  </book4> </tns:collection>');

-- --------------------------------------------------------

--
-- Table structure for table `xsd`
--

CREATE TABLE `xsd` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xsd`
--

INSERT INTO `xsd` (`uuid`, `content`) VALUES
('05b88faa-6909-4444-aadc-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\"   targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\">  <element name=\"collection\">   <complexType>    <sequence>     <element name=\"disc4\" type=\"tns:disc4\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"movie4\" type=\"tns:movie4\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>     <element name=\"book4\" type=\"tns:book4\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>    </sequence>   </complexType>  </element>      <complexType name=\"item\">      <sequence>       <element name=\"name\" type=\"string\"/>      </sequence>      <attribute name=\"year\" type=\"unsignedInt\"/>     </complexType>      <complexType name=\"disc4\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"artist\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"movie4\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"director\" type=\"string\"/>        </sequence>       </extension>      </complexContent>     </complexType>      <complexType name=\"book4\">      <complexContent>       <extension base=\"tns:item\">        <sequence>         <element name=\"genre\" type=\"string\"/>         <element name=\"author\" type=\"string\"/>         <element name=\"pages\" type=\"unsignedInt\"/>        </sequence>       </extension>      </complexContent>     </complexType> </schema>'),
('e5b64c34-636c-4444-2222-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\" xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" elementFormDefault=\"qualified\">  <element name=\"people\">   <complexType>    <sequence>     <element name=\"person2\">      <complexType>       <sequence>        <element name=\"name\" type=\"string\" minOccurs=\"1\" maxOccurs=\"1\"/>       </sequence>       <attribute name=\"dni\" type=\"string\"/>      </complexType>     </element>    </sequence>   </complexType>  </element> </schema>'),
('e5b64c34-636c-4444-b729-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <schema xmlns=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.esei.uvigo.es/dai/proyecto\" xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" elementFormDefault=\"qualified\">  <element name=\"people\">   <complexType>    <sequence>     <element name=\"person4\">      <complexType>       <sequence>        <element name=\"name\" type=\"string\" minOccurs=\"1\" maxOccurs=\"1\"/>       </sequence>       <attribute name=\"dni\" type=\"string\"/>      </complexType>     </element>    </sequence>   </complexType>  </element> </schema>');

-- --------------------------------------------------------

--
-- Table structure for table `xslt`
--

CREATE TABLE `xslt` (
  `uuid` char(36) COLLATE utf8_spanish2_ci NOT NULL,
  `content` text COLLATE utf8_spanish2_ci NOT NULL,
  `xsd` char(36) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `xslt`
--

INSERT INTO `xslt` (`uuid`, `content`, `xsd`) VALUES
('f260dfee-636c-4444-bbdd-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>People</title>    </head>    <body>     <div>      <h1>People</h1>      <xsl:apply-templates select=\"tns:people/tns:person4\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"tns:person4\">   <div class=\"person4\">    <h3>DNI: <xsl:value-of select=\"@dni\"/></h3>    <div class=\"name\"><xsl:value-of select=\"tns:name\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', 'e5b64c34-636c-4444-b729-685b35c84fb4'),
('f260dfee-636c-4444-2222-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>People</title>    </head>    <body>     <div>      <h1>People</h1>      <xsl:apply-templates select=\"tns:people/tns:person2\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"tns:person2\">   <div class=\"person2\">    <h3>DNI: <xsl:value-of select=\"@dni\"/></h3>    <div class=\"name\"><xsl:value-of select=\"tns:name\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', 'e5b64c34-636c-4444-2222-685b35c84fb4'),
('1fd26c94-6909-4444-9a75-685b35c84fb4', '<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"  xmlns:tns=\"http://www.esei.uvigo.es/dai/proyecto\" version=\"1.0\">   <xsl:output method=\"html\" indent=\"yes\" encoding=\"utf-8\" />   <xsl:template match=\"/\">   <xsl:text disable-output-escaping=\"yes\">&lt;!DOCTYPE html&gt;</xsl:text>   <html>    <head>     <title>Collection</title>    </head>    <body>     <div>      <h1>Collection</h1>      <h2>Discs 4</h2>      <xsl:apply-templates select=\"tns:collection/disc4\" />      <hr/>      <h2>Movies 4</h2>      <xsl:apply-templates select=\"tns:collection/movie4\" />      <hr/>      <h2>Books 4</h2>      <xsl:apply-templates select=\"tns:collection/book4\" />     </div>    </body>   </html>  </xsl:template>   <xsl:template match=\"disc4\">   <div class=\"disc4\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"artist\">Artist: <xsl:value-of select=\"artist\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"movie4\">   <div class=\"movie4\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"director\">Director: <xsl:value-of select=\"director\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>   </div>  </xsl:template>   <xsl:template match=\"book4\">   <div class=\"book4\">    <h3><xsl:value-of select=\"name\"/>(<xsl:value-of select=\"@year\"/>)</h3>    <div class=\"author\">Author: <xsl:value-of select=\"author\" /></div>    <div class=\"genre\">Genre: <xsl:value-of select=\"genre\" /></div>    <div class=\"pages\">Pages: <xsl:value-of select=\"pages\" /></div>   </div>  </xsl:template> </xsl:stylesheet>', '05b88faa-6909-4444-aadc-685b35c84fb4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `html`
--
ALTER TABLE `html`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xml`
--
ALTER TABLE `xml`
  ADD PRIMARY KEY (`uuid`);

--
-- Indexes for table `xsd`
--
ALTER TABLE `xsd`
  ADD PRIMARY KEY (`uuid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

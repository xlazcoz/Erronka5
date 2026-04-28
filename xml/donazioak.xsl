<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Donazioen Txostena</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; color: #333; }
                    h1 { color: #2c3e50; text-align: center; }
                    h2 { color: #2980b9; border-bottom: 2px solid #bdc3c7; padding-bottom: 5px; margin-top: 40px;}
                    table { border-collapse: collapse; width: 100%; margin-top: 15px; }
                    th, td { border: 1px solid #bdc3c7; padding: 10px; text-align: left; }
                    th { background-color: #ecf0f1; color: #2c3e50; }
                    .mota { font-weight: bold; color: #d35400; text-transform: capitalize; }
                    .badge { background-color: #e74c3c; color: white; padding: 3px 8px; border-radius: 4px; font-size: 12px; }
                </style>
            </head>
            <body>
                <h1>Elikagaien Donazioak (2026)</h1>
                
                <xsl:for-each select="donazioak/donazioa">
                    
                    <h2>
                        <xsl:value-of select="enpresa_emailea/izena"/> 
                        (IFK: <xsl:value-of select="enpresa_emailea/ifk"/>)
                    </h2>
                    <p><strong>Donazio data:</strong> <xsl:value-of select="donazio_data"/></p>
                    
                    <table>
                        <tr>
                            <th>Erreferentzia</th>
                            <th>Elikagaia</th>
                            <th>Mota</th>
                            <th>Kopurua</th>
                            <th>Fabrikatzailea</th>
                            <th>Iraungitze data</th>
                            <th>Berezitasunak</th>
                        </tr>
                        
                        <xsl:for-each select="elikagaiak/elikagaia">
                            <tr>
                                <td><xsl:value-of select="erreferentzia"/></td>
                                <td><xsl:value-of select="izena"/></td>
                                <td class="mota"><xsl:value-of select="@mota"/></td>
                                <td><xsl:value-of select="kopurua"/></td>
                                <td><xsl:value-of select="enpresa_fabrikatzailea"/></td>
                                
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="iraungitze_data">
                                            <xsl:value-of select="iraungitze_data"/>
                                        </xsl:when>
                                        <xsl:otherwise>---</xsl:otherwise>
                                    </xsl:choose>
                                </td>
                                
                                <td>
                                    <xsl:if test="hoztuta_mantendu = 'BAI'">
                                        <span class="badge">Hoztuta (Frío)</span>
                                    </xsl:if>
                                    <xsl:if test="kontserba = 'BAI'">
                                        <span class="badge" style="background-color: #27ae60;">Kontserba</span>
                                    </xsl:if>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </xsl:for-each>
                
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
<?bg.kirilov.filesystem2html.utils.xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <style type="text/css">
                ul.folder {list-style-image: url('folder.jpeg')}
                li.file {list-style-image: url('file.jpeg')}
            </style>
            <body>
                <h2>File System 2 HTML program</h2>
                <ul class='folder'>
                    <xsl:apply-templates select="FileSystemTree/DirectoryNode"/>
                    <xsl:apply-templates select="FileSystemTree/FileNode"/>
                </ul>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="DirectoryNodeTemplate" match="DirectoryNode">
        <li>
            <xsl:value-of select="@Name"/>
            <ul>
                <xsl:apply-templates select="DirectoryNode"/>
                <xsl:apply-templates select="FileNode"/>
            </ul>
        </li>
    </xsl:template>

    <xsl:template name="FileNodeTemplate" match="FileNode">
        <li class="file">
            <xsl:value-of select="@Name"/> -
            <b>Size</b>
            =
            <xsl:value-of select="@Size"/> Bytes;
            <b>LastDateChange</b>
            = <xsl:value-of select="@LastDateChanged"/>;
            <b>Executable</b>
            = <xsl:value-of select="@Executable"/>;
            <b>Hidden</b>
            = <xsl:value-of select="@Hidden"/>;
            <b>Readable</b>
            = <xsl:value-of select="@Readable"/>;
            <b>Writable</b>
            = <xsl:value-of select="@Writable"/>;
        </li>
    </xsl:template>
</xsl:stylesheet>
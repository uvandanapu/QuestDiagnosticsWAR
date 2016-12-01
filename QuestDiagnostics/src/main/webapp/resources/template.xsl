<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:barcode="org.krysalis.barcode4j.xalan.BarcodeExt" xmlns:common="http://exslt.org/common"
	xmlns:xalan="http://xml.apache.org" exclude-result-prefixes="barcode common xalan">
	<xsl:template match="PatientsData">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simple"
					page-height="20cm" page-width="15.5cm" margin-left="0.2cm"
					margin-right="0.2cm">
					<fo:region-body margin-top="0.5cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simple">
				<fo:flow flow-name="xsl-region-body">

					<fo:block font-family="any" font-size="26pt" font-weight="bold" space-after="8mm">Quest Diagnostics - Patients Record</fo:block>
					<fo:block font-family="any" font-size="10pt" font-weight="normal">
						<fo:table table-layout="fixed" width="100%">
							<fo:table-column column-number="1" column-width="3cm" />
							<fo:table-column column-number="2" column-width="3cm" />
							<fo:table-column column-number="3" column-width="3cm" />
							<fo:table-column column-number="4" column-width="3cm" />
							<fo:table-column column-number="5" column-width="3cm" />

							<fo:table-body height="10cm" border-collapse="separate">
								<xsl:if test="./patientsList/patientInfo">
									<xsl:for-each select="./patientsList/patientInfo">
										<fo:table-row border="solid 0.1mm black">

											<fo:table-cell text-align="center">
												<fo:block>
													<xsl:value-of select="fName" />
												</fo:block>

											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>
													<xsl:value-of select="lName" />
												</fo:block>

											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>
													<xsl:value-of select="eMail" />
												</fo:block>

											</fo:table-cell>
											<fo:table-cell text-align="center">
												<fo:block>
													<xsl:value-of select="dob" />
												</fo:block>

											</fo:table-cell>
											<fo:table-cell text-align="left">
												<fo:block>
													<xsl:value-of select="pNum" />
												</fo:block>

											</fo:table-cell>



										</fo:table-row>
									</xsl:for-each>
								</xsl:if>
								<xsl:if test="not(./patientsList/patientInfo)">
									<fo:table-cell>
										<fo:block />
									</fo:table-cell>
								</xsl:if>
							</fo:table-body>
						</fo:table>
					</fo:block>

				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>
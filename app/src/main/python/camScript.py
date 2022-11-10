import camelot

def getLocation(df, value):
    return df.where(df.eq(value)).stack().index.tolist()[0]

def getData(pdfURL):
# def getData():
#     tables = camelot.read_pdf('https://ciclabs.000webhostapp.com/INVOICE%206341.pdf', flavor='stream')
    tables = camelot.read_pdf(pdfURL, flavor='stream')

    # print(tables)             # camelot TableList

    # print(tables[0])          # camelot Table
    # print(tables[0].df)       # pandas DataFrame

    # print(tables[1])
    # print(tables[1].df)

    shipper_name = ""
    shipper_address = ""
    consignee_name = ""
    consignee_address = ""
    buyer_name = ""
    buyer_address = ""
    invoice_no = ""
    invoice_date = ""
    invoice_no_and_date = ""
    port_of_loading = ""
    port_of_discharge = ""
    final_destination = ""
    description_of_goods = ""
    total_gross_weight = ""
    total_net_weight = ""
    total_no_of_bags = ""
    packing = ""

    for table in range(0, len(tables)):

        # Converting camelot Table into pandas DataFrame
        df = tables[0].df
        rows, cols = df.shape

        for row in range (0, rows):
            for col in range (0, cols):
                if "Exporter" in df.loc[row, col]:
                    if len(shipper_name)==0:
                        shipper_name = df.loc[row+1,col].replace(",","")
                    if len(shipper_address)==0:
                        shipper_address = df.loc[row+2,col] + df.loc[row+3,col]

                elif "Consignee" in df.loc[row,col]:
                    if len(consignee_name)==0:
                        consignee_name = df.loc[row+1,col].replace(",","")
                    if len(consignee_address)==0:
                        consignee_address = df.loc[row+2,col] + " " + df.loc[row+3,col]

                elif "Buyer" in df.loc[row,col]:
                    if len(buyer_name)==0:
                        buyer_name = df.loc[row+1,col]
                    if len(buyer_address)==0:
                        buyer_address = df.loc[row+2,col] + " " + df.loc[row+3,col]

                elif "Invoice No" in df.loc[row,col]:
                    if len(invoice_no_and_date)==0:
                        invoice_no_and_date = df.loc[row+1,col].split()
                        invoice_no = invoice_no_and_date[0]
                        invoice_date = invoice_no_and_date[-1]

                elif "Port Of Loading" in df.loc[row,col]:
                    if(len(port_of_loading)==0):
                        port_of_loading = df.loc[row+1,col]

                elif "Port of Discharge" in df.loc[row,col]:
                    if(len(port_of_discharge)==0):
                        port_of_discharge = df.loc[row+1,col]

                elif "Country of Final Destination" in df.loc[row,col]:
                    pass

                elif "Final Destination" in df.loc[row,col]:
                    if(len(final_destination)==0):
                        final_destination = df.loc[row+1,col]

                elif "BAGS" in df.loc[row,col]:
                    if(len(packing)==0):
                        splited = df.loc[row,col].split()
                        for word in splited:
                            if word.isnumeric():
                                a = splited.index(word)
                                break
                        b = splited.index("BAGS")
                        packing = " ".join(splited[a+1 : b+1])
#     print("\nSHIPPER NAME:", shipper_name)
#     print("SHIPPER ADDRESS:", shipper_address)
#     print("CONSIGNEE NAME:", consignee_name)
#     print("CONSIGNEE ADDRESS:", consignee_address)
#     print("BUYER NAME:", buyer_name)
#     print("BUYER ADDRESS:", buyer_address)
#     print("INVOICE NO.:", invoice_no)
#     print("INVOICE DATE:", invoice_date)
#     print("PORT OF LOADING:", port_of_loading)
#     print("PORT OF DISCHARGE:", port_of_discharge)
#     print("FINAL DESTINATION:", final_destination)
#     print("DESCRIPTION OF GOODS:")
#     print("TOTAL GROSS WEIGHT:")
#     print("TOTAL NET WEIGHT:")
#     print("TOTAL NO. OF BAGS:")
#     print("PACKING:", packing)

    return shipper_name, shipper_address, consignee_name, consignee_address, buyer_name, buyer_address, invoice_no, invoice_date, port_of_loading, port_of_discharge, final_destination, description_of_goods, total_gross_weight, total_net_weight, total_no_of_bags, packing

# getData("https://ciclabs.000webhostapp.com/INVOICE%206341.pdf")
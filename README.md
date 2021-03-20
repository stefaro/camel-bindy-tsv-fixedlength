# camel-bindy-tsv-fixedlength
Camel example: reading a TSV file with bindy using FixedLengthRecord

Data from TSV file is read to POJO and then converted to XML.

# Example with data

You can find this data form src/test/resources. Project also has a unit test for it.

## Input data

This is simple sales data, which consists of header followed by sales lines.

```
31	name	2018-11-01	2018-10-31 12:05:16	3   
1	10	1.00	13.00	14  
2	20	1.00	14.00	14  
3	30	1.00	15.00	14  
```

## Output data

As an output we get an XML file.

``` xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xmlOrder>
    <date1>2018-11-01T00:00:00+02:00</date1>
    <date2>2018-10-31T00:05:16+02:00</date2>
    <id>31</id>
    <name>name</name>
    <numerOfFile>3</numerOfFile>
    <lines>
        <count>13</count>
        <foreignId>14</foreignId>
        <id>10</id>
        <lp>1</lp>
        <priceByOne>1</priceByOne>
    </lines>
    <lines>
        <count>14</count>
        <foreignId>14</foreignId>
        <id>20</id>
        <lp>2</lp>
        <priceByOne>1</priceByOne>
    </lines>
    <lines>
        <count>15</count>
        <foreignId>14</foreignId>
        <id>30</id>
        <lp>3</lp>
        <priceByOne>1</priceByOne>
    </lines>
</xmlOrder>
```

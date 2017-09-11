package ru.currencyconverter.net.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class ValCurs {

    @Attribute(name = "Date")
    private String date;
    @Attribute(name = "name")
    private String name;
    @ElementList(inline = true, name = "Currency")
    private List<Valute> list;

    public ValCurs(){
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<Valute> getList() {
        return list;
    }

}

package ru.mail.krivonos.project_jd1.services.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Items")
public class XMLItemsDTO {

    private List<XMLItemDTO> xmlItemDTOList;

    public List<XMLItemDTO> getXmlItemDTOList() {
        return xmlItemDTOList;
    }

    @XmlElement(name = "Item")
    public void setXmlItemDTOList(List<XMLItemDTO> xmlItemDTOList) {
        this.xmlItemDTOList = xmlItemDTOList;
    }
}

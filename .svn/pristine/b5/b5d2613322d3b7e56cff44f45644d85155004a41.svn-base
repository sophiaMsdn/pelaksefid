/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.v1;

import ir.aria.pelaksefid.domain.entity.Document;
import ir.aria.pelaksefid.domain.repository.DocumentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mana2
 */
@RestController
@RequestMapping(path = "/documents",
        method = {RequestMethod.GET}
)
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping(value = "/getImageById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> getArticleImage(@PathVariable Long id) {
        Optional<Document> optional = documentRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        Document document = optional.get();
        byte[] image = document.getContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);

        return new HttpEntity<>(image, headers);
    }
}

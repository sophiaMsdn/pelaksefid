/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.domain.entity.AdvertiseDocument;
import ir.aria.pelaksefid.domain.entity.Document;
import ir.aria.pelaksefid.domain.model.AdvertiseDocumentDto;
import ir.aria.pelaksefid.domain.repository.AdvertiseDocumentRepository;
import ir.aria.pelaksefid.domain.repository.DocumentRepository;
import ir.aria.pelaksefid.service.base.BaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mana2
 */
@Service
public class AdvertiseDocumentService extends BaseService {

    @Autowired
    private AdvertiseDocumentRepository repository;
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getDocumentsFromAdvertiseDocument(Set<AdvertiseDocument> advertiseDocuments) {
        List<Document> documents;
        documents = new ArrayList<>();
        for (AdvertiseDocument sd : advertiseDocuments) {
            Optional<Document> document = documentRepository.findById(sd.getDocId());
            if (!document.isPresent()) {
                return null;
            }
            documents.add(document.get());
        }
        return documents;
    }

    public List<Document> getDocumentsFromAdvertiseDocument(AdvertiseDocumentDto[] advertiseDocuments) {
        List<Document> documents = new ArrayList<>();
        for (AdvertiseDocumentDto sd : advertiseDocuments) {
            Optional<Document> document = documentRepository.findById(Long.valueOf(sd.getDocId()));
            if (!document.isPresent()) {
                return null;
            }
            documents.add(document.get());
        }
        return documents;
    }
}

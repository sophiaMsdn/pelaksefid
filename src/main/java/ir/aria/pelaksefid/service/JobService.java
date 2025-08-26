/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import ir.aria.pelaksefid.client.SigmaServiceClient;
import ir.aria.pelaksefid.client.consume.sigma.Advertise;
import ir.aria.pelaksefid.client.consume.sigma.AdvertiseRes;
import ir.aria.pelaksefid.domain.entity.Account;
import ir.aria.pelaksefid.domain.entity.AdvertiseDocument;
import ir.aria.pelaksefid.domain.entity.Car;
import ir.aria.pelaksefid.domain.entity.CarColor;
import ir.aria.pelaksefid.domain.entity.CarModel;
import ir.aria.pelaksefid.domain.entity.CarTrimColor;
import ir.aria.pelaksefid.domain.entity.CarType;
import ir.aria.pelaksefid.domain.entity.ManufactureYear;
import ir.aria.pelaksefid.domain.entity.Region;
import ir.aria.pelaksefid.domain.enumaration.AdvertiseStateEnm;
import ir.aria.pelaksefid.domain.enumaration.RegionTypeEnm;
import ir.aria.pelaksefid.domain.repository.AccountRepository;
import ir.aria.pelaksefid.domain.repository.AdvertiseDocumentRepository;
import ir.aria.pelaksefid.domain.repository.AdvertiseRepository;
import ir.aria.pelaksefid.domain.repository.BrandRepository;
import ir.aria.pelaksefid.domain.repository.CarColorRepository;
import ir.aria.pelaksefid.domain.repository.CarModelRepository;
import ir.aria.pelaksefid.domain.repository.CarRepository;
import ir.aria.pelaksefid.domain.repository.CarTrimColorRepository;
import ir.aria.pelaksefid.domain.repository.CarTypeRepository;
import ir.aria.pelaksefid.domain.repository.ManufactureYearRepository;
import ir.aria.pelaksefid.domain.repository.RegionRepository;
import ir.aria.pelaksefid.utility.ValidationUtil;
import ir.aria.pelaksefid.web.v1.model.request.AdvertiseFilterReq;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mana2
 */
@Component
public class JobService {

    @Autowired
    private CarModelRepository carModelRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private ManufactureYearRepository manufactureYearRepository;
    @Autowired
    private CarColorRepository carColorRepository;
    @Autowired
    private CarTrimColorRepository carTrimColorRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AdvertiseRepository advertiseRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AdvertiseDocumentRepository advertiseDocumentRepository;

    @Transactional
    @Scheduled(cron = "0 0 0/2 * * *")
//    @Scheduled(cron = "0 1/2 * * * *")
    public void getSigmaAdvertises() {
        getSigmaAdvertisesInternal();
    }

    @Transactional
    public void getSigmaAdvertisesInternal() {
        AdvertiseFilterReq req = new AdvertiseFilterReq();
        AdvertiseRes ads = SigmaServiceClient.getAllAdvertises(req);

        Set<Long> existingIds;
        try ( Stream<Long> idStream = advertiseRepository.findSigmaAdvertisIds()) {
            existingIds = idStream.collect(Collectors.toSet());
        }
        for (Advertise ad : ads.getSalesOrders()) {
            try {

                Long adId = Long.parseLong(ad.getId());
                ir.aria.pelaksefid.domain.entity.Advertise advertise;
                if (!existingIds.contains(adId)) {
                    advertise = new ir.aria.pelaksefid.domain.entity.Advertise();
                } else {
                    advertise = advertiseRepository.findBySigmaId(adId);
                }
                Account account = accountRepository.findByIsMana(Boolean.TRUE);
                advertise.setAccount(account);
                if (!ValidationUtil.isEmpty(ad.getAdvertiseAmount())) {
                    advertise.setPrice(BigDecimal.valueOf(Double.parseDouble(ad.getAdvertiseAmount())));
                } else {
                    advertise.setPrice(BigDecimal.ZERO);
                }

                advertise.setComment(ad.getComment());
                advertise.setIsActive(Boolean.TRUE);
                advertise.setIsAlive(Boolean.TRUE);
                advertise.setAdvertiseState(AdvertiseStateEnm.CONFIRM);
                advertise.setSigma(Boolean.TRUE);
                advertise.setSigmaId(adId);

//                    List<Brand> brands = brandRepository.findByDescription(ad.getBrandDescription());
                List<CarModel> carModels = carModelRepository.findByDescription(ad.getCarModelDescription());

                List<CarType> carTypes = carTypeRepository.findByDescription(ad.getCarTypeDescription());

                CarType carType = null;
                for (CarModel cm : carModels) {
                    for (CarType ct : carTypes) {
                        if (ct.getCarModel().getId().compareTo(cm.getId()) == 0) {
                            carType = ct;
                        }
                    }
                }
//                    if (carType == null) {
//                        Brand brand = null;
//                        CarModel carModel = null;
//                        if (brands.isEmpty()) {
//                            BrandDto brandDto = new BrandDto();
//                            brandDto.setDescription(ad.getBrandDescription());
//                            brand = (Brand) brandDto.toEntity();
//                            brand = brandRepository.save(brand);
//                        }
//                        if (carModels.isEmpty()) {
//                            CarModelDto carModelDto = new CarModelDto();
//                            carModelDto.setDescription(ad.getCarModelDescription());
//                            carModel = (CarModel) carModelDto.toEntity();
//                            carModel.setBrand(brand);
//                            carModel = carModelRepository.save(carModel);
//                        }
//                        CarTypeDto carTypeDto = new CarTypeDto();
//                        carTypeDto.setDescription(ad.getCarTypeDescription());
//                        carType = (CarType) carTypeDto.toEntity();
//                        carType.setCarModel(carModel);
//                        carType = carTypeRepository.save(carType);
//                    }
                if (carType == null) {
                    continue;
                }
                Car car = new Car();
                List<ManufactureYear> manufactureYear = manufactureYearRepository.findByPersianYear(Integer.valueOf(ad.getPersianYear()));
                if (manufactureYear.isEmpty()) {
                    continue;
                }

                car.setManufactureYear(manufactureYear.get(0));

                Optional<CarColor> carColor = carColorRepository.findById(Long.valueOf(ad.getColorId()));
                if (!carColor.isPresent()) {
                    continue;
                }
                car.setCarColor(carColor.get());

                Optional<CarTrimColor> carTrimColor = carTrimColorRepository.findById(Long.valueOf(ad.getTrimColorId()));
                if (!carTrimColor.isPresent()) {
                    continue;
                }
                car.setCarTrimColor(carTrimColor.get());

                if (!ValidationUtil.isEmpty(ad.getMileage())) {
                    car.setMileage(BigDecimal.valueOf(Double.parseDouble(ad.getMileage())));
                } else {
                    car.setMileage(BigDecimal.ZERO);
                }

                List<Region> province = regionRepository.findByDescriptionAndType(ad.getCityDescription(), RegionTypeEnm.CTY);
                if (province.isEmpty()) {
                    continue;
                }

                advertise.setRegion(province.get(0));
                car.setCarType(carType);
                car.setRegDate(new Date());
                car.setUpdateDate(new Date());
                carRepository.save(car);
                advertise.setCar(car);
                advertise.setRegDate(new Date());
                advertise.setUpdateDate(new Date());
                advertise = advertiseRepository.save(advertise);
                List<AdvertiseDocument> oldDocs = advertiseDocumentRepository.findByIsActiveAndIsAliveAndAdvertise(Boolean.TRUE, Boolean.TRUE, advertise);
                for (AdvertiseDocument a : oldDocs) {
                    advertiseDocumentRepository.delete(a);
                }
                for (ir.aria.pelaksefid.client.consume.sigma.Document d : ad.getSalesOrderDocuments()) {
                    AdvertiseDocument advertiseDocument = new AdvertiseDocument();
                    advertiseDocument.setDocId(Long.valueOf(d.getDocId()));
                    advertiseDocument.setAdvertise(advertise);
                    advertiseDocument.setIsActive(Boolean.TRUE);
                    advertiseDocument.setIsAlive(Boolean.TRUE);
                    advertiseDocument.setRegDate(new Date());
                    advertiseDocument.setUpdateDate(new Date());
                    advertiseDocumentRepository.save(advertiseDocument);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

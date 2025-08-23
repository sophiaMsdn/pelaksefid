/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.web.v1;

import com.google.gson.Gson;
import ir.aria.pelaksefid.client.ManaTecServiceClient;
import ir.aria.pelaksefid.client.SigmaServiceClient;
import ir.aria.pelaksefid.client.consume.sigma.ManaPrice;
import ir.aria.pelaksefid.client.consume.woocommerce.NewRes;
import ir.aria.pelaksefid.domain.entity.Brand;
import ir.aria.pelaksefid.domain.entity.Cache;
import ir.aria.pelaksefid.domain.entity.CarBody;
import ir.aria.pelaksefid.domain.entity.CarBodyStatus;
import ir.aria.pelaksefid.domain.entity.CarColor;
import ir.aria.pelaksefid.domain.entity.CarCylinder;
import ir.aria.pelaksefid.domain.entity.CarDifferential;
import ir.aria.pelaksefid.domain.entity.CarEngine;
import ir.aria.pelaksefid.domain.entity.CarFuel;
import ir.aria.pelaksefid.domain.entity.CarGear;
import ir.aria.pelaksefid.domain.entity.CarModel;
import ir.aria.pelaksefid.domain.entity.CarOrigin;
import ir.aria.pelaksefid.domain.entity.CarTrimColor;
import ir.aria.pelaksefid.domain.entity.CarTypeColor;
import ir.aria.pelaksefid.domain.entity.CarTypeFuel;
import ir.aria.pelaksefid.domain.entity.CarTypeManufactureYear;
import ir.aria.pelaksefid.domain.entity.CarType;
import ir.aria.pelaksefid.domain.entity.CarTypeTrimColor;
import ir.aria.pelaksefid.domain.entity.ManufactureYear;
import ir.aria.pelaksefid.domain.entity.Region;
import ir.aria.pelaksefid.domain.entity.Rule;
import ir.aria.pelaksefid.domain.enumaration.CacheEnm;
import ir.aria.pelaksefid.domain.enumaration.RegionTypeEnm;
import ir.aria.pelaksefid.domain.enumaration.ResultEnm;
import ir.aria.pelaksefid.domain.model.BodyStatusDto;
import ir.aria.pelaksefid.domain.model.BrandDto;
import ir.aria.pelaksefid.domain.model.CarBodyDto;
import ir.aria.pelaksefid.domain.model.CarColorDto;
import ir.aria.pelaksefid.domain.model.CarCylinderDto;
import ir.aria.pelaksefid.domain.model.CarDifferentialDto;
import ir.aria.pelaksefid.domain.model.CarEngineDto;
import ir.aria.pelaksefid.domain.model.CarFuelDto;
import ir.aria.pelaksefid.domain.model.CarGearDto;
import ir.aria.pelaksefid.domain.model.CarTypeColorDto;
import ir.aria.pelaksefid.domain.model.CarModelDto;
import ir.aria.pelaksefid.domain.model.CarOriginDto;
import ir.aria.pelaksefid.domain.model.CarTrimColorDto;
import ir.aria.pelaksefid.domain.model.CarTypeFuelDto;
import ir.aria.pelaksefid.domain.model.CarTypeManufactureYearDto;
import ir.aria.pelaksefid.domain.model.CarTypeDto;
import ir.aria.pelaksefid.domain.model.CarTypeTrimColorDto;
import ir.aria.pelaksefid.domain.model.ManaPriceDto;
import ir.aria.pelaksefid.domain.model.ManufactureYearDto;
import ir.aria.pelaksefid.domain.model.NewDto;
import ir.aria.pelaksefid.domain.model.RegionDto;
import ir.aria.pelaksefid.domain.model.RuleDto;
import ir.aria.pelaksefid.domain.repository.BrandRepository;
import ir.aria.pelaksefid.domain.repository.CacheRepository;
import ir.aria.pelaksefid.domain.repository.CarBodyRepository;
import ir.aria.pelaksefid.domain.repository.CarBodyStatusRepository;
import ir.aria.pelaksefid.domain.repository.CarColorRepository;
import ir.aria.pelaksefid.domain.repository.CarCylinderRepository;
import ir.aria.pelaksefid.domain.repository.CarDifferentialRepository;
import ir.aria.pelaksefid.domain.repository.CarEngineRepository;
import ir.aria.pelaksefid.domain.repository.CarFuelRepository;
import ir.aria.pelaksefid.domain.repository.CarGearRepository;
import ir.aria.pelaksefid.domain.repository.CarModelRepository;
import ir.aria.pelaksefid.domain.repository.CarOriginRepository;
import ir.aria.pelaksefid.domain.repository.CarTrimColorRepository;
import ir.aria.pelaksefid.domain.repository.RegionRepository;
import ir.aria.pelaksefid.service.CacheService;
import ir.aria.pelaksefid.utility.ValidationUtil;
import ir.aria.pelaksefid.web.v1.model.request.RegionReq;
import ir.aria.pelaksefid.web.v1.model.request.base.BaseReq;
import ir.aria.pelaksefid.web.v1.model.response.AllCarsRes;
import ir.aria.pelaksefid.web.v1.model.response.BodyStatusesRes;
import ir.aria.pelaksefid.web.v1.model.response.RegionsRes;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ir.aria.pelaksefid.domain.repository.CarTypeColorRepository;
import ir.aria.pelaksefid.domain.repository.CarTypeFuelRepository;
import ir.aria.pelaksefid.domain.repository.CarTypeManufactureYearRepository;
import ir.aria.pelaksefid.domain.repository.CarTypeRepository;
import ir.aria.pelaksefid.domain.repository.CarTypeTrimColorRepository;
import ir.aria.pelaksefid.domain.repository.ManufactureYearRepository;
import ir.aria.pelaksefid.service.RuleService;
import ir.aria.pelaksefid.web.v1.model.request.base.BaseListReq;
import ir.aria.pelaksefid.web.v1.model.response.CarAllDataRes;
import ir.aria.pelaksefid.web.v1.model.response.ManaPriceRes;
import ir.aria.pelaksefid.web.v1.model.response.NewsRes;
import ir.aria.pelaksefid.web.v1.model.response.RuleRes;

/**
 *
 * @author 2
 */
@RestController
@RequestMapping(path = "${apiv1}/configurations",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.POST})
public class ConfigurationController {

    @Autowired
    private CacheRepository cacheRepository;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CarModelRepository carModelRepository;
    @Autowired
    private CarTypeManufactureYearRepository carTypeManufactureYearRepository;
    @Autowired
    private CarTypeColorRepository carTypeColorRepository;
    @Autowired
    private CarTypeTrimColorRepository carTypeTrimColorRepository;
    @Autowired
    private CarTypeFuelRepository carTypeFuelRepository;
    @Autowired
    private CarBodyStatusRepository carBodyStatusRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private CarBodyRepository carBodyRepository;
    @Autowired
    private CarColorRepository carColorRepository;
    @Autowired
    private CarGearRepository carGearRepository;
    @Autowired
    private CarFuelRepository carFuelRepository;
    @Autowired
    private CarCylinderRepository carCylinderRepository;
    @Autowired
    private CarDifferentialRepository carDifferentialRepository;
    @Autowired
    private CarEngineRepository carEngineRepository;
    @Autowired
    private CarTrimColorRepository carTrimColorRepository;
    @Autowired
    private CarOriginRepository carOriginRepository;
    @Autowired
    private ManufactureYearRepository manufactureYearRepository;
    @Autowired
    private RuleService ruleService;

    private static final Gson gson = new Gson();

    @PostMapping(path = "/generateAllCarsCache")
    public String generateAllCarsCache(@RequestBody BaseReq req,
            HttpServletRequest request) {

        AllCarsRes res = new AllCarsRes();

        List<Brand> brands = brandRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        BrandDto[] brandDtos = new BrandDto[brands.size()];
        int cntr = 0;
        for (Brand b : brands) {
            BrandDto brandDto = new BrandDto();
            brandDto.fromEntity(b);
            List<CarModel> carModels = carModelRepository.findByIsAliveAndIsActiveAndBrand(Boolean.TRUE, Boolean.TRUE, b);
            CarModelDto[] carModelDtos = new CarModelDto[carModels.size()];
            int cntr2 = 0;
            for (CarModel cm : carModels) {
                CarModelDto carModelDto = new CarModelDto();
                carModelDto.fromEntity(cm);

                List<CarType> carTypes = carTypeRepository.findByIsAliveAndIsActiveAndCarModel(Boolean.TRUE, Boolean.TRUE, cm);
                CarTypeDto[] carTypeDtos = new CarTypeDto[carTypes.size()];
                int cntr3 = 0;
                for (CarType ct : carTypes) {
                    CarTypeDto carTypeDto = new CarTypeDto();
                    carTypeDto.fromEntity(ct);

                    List<CarTypeManufactureYear> carModelManufactureYears = carTypeManufactureYearRepository.findByIsAliveAndIsActiveAndCarType(Boolean.TRUE, Boolean.TRUE, ct);
                    CarTypeManufactureYearDto[] manufactureYearDtos = new CarTypeManufactureYearDto[carModelManufactureYears.size()];
                    int cntr4 = 0;
                    for (CarTypeManufactureYear my : carModelManufactureYears) {
                        CarTypeManufactureYearDto manufactureYearDto = new CarTypeManufactureYearDto();
                        manufactureYearDto.fromEntity(my);
                        manufactureYearDtos[cntr4++] = manufactureYearDto;
                    }
                    carTypeDto.setCarTypeManufactureYears(manufactureYearDtos);

                    List<CarTypeColor> carTypeColors = carTypeColorRepository.findByIsAliveAndIsActiveAndCarType(Boolean.TRUE, Boolean.TRUE, ct);
                    CarTypeColorDto[] carTypeColorDtos = new CarTypeColorDto[carTypeColors.size()];
                    int cntr5 = 0;
                    for (CarTypeColor cl : carTypeColors) {
                        CarTypeColorDto carTypeColorDto = new CarTypeColorDto();
                        carTypeColorDto.fromEntity(cl);
                        carTypeColorDtos[cntr5++] = carTypeColorDto;
                    }
                    carTypeDto.setCarTypeColors(carTypeColorDtos);
                    List<CarTypeTrimColor> carTypeTrimColors = carTypeTrimColorRepository.findByIsAliveAndIsActiveAndCarType(Boolean.TRUE, Boolean.TRUE, ct);
                    CarTypeTrimColorDto[] carTypeTrimColorDtos = new CarTypeTrimColorDto[carTypeTrimColors.size()];
                    int cntr6 = 0;
                    for (CarTypeTrimColor cl : carTypeTrimColors) {
                        CarTypeTrimColorDto carTypeTrimColorDto = new CarTypeTrimColorDto();
                        carTypeTrimColorDto.fromEntity(cl);
                        carTypeTrimColorDto.setCarTypeId(cm.getId().toString());
                        carTypeTrimColorDtos[cntr6++] = carTypeTrimColorDto;
                    }
                    carTypeDto.setCarTypeTrimColors(carTypeTrimColorDtos);

                    List<CarTypeFuel> carTypeFuels = carTypeFuelRepository.findByIsAliveAndIsActiveAndCarType(Boolean.TRUE, Boolean.TRUE, ct);
                    CarTypeFuelDto[] carTypeFuelDtos = new CarTypeFuelDto[carTypeFuels.size()];
                    int cntr8 = 0;
                    for (CarTypeFuel cl : carTypeFuels) {
                        CarTypeFuelDto carTypeFuelDto = new CarTypeFuelDto();
                        carTypeFuelDto.fromEntity(cl);
                        carTypeFuelDtos[cntr8++] = carTypeFuelDto;
                    }
                    carTypeDto.setCarTypeFuels(carTypeFuelDtos);

                    carTypeDtos[cntr3++] = carTypeDto;
                }
                carModelDto.setCarTypes(carTypeDtos);
                carModelDtos[cntr2++] = carModelDto;
            }
            brandDtos[cntr++] = brandDto;
            brandDto.setCarModels(carModelDtos);
        }
        res.setBrands(brandDtos);
        Cache cache = cacheService.controlAndSave(CacheEnm.CR, gson.toJson(res));
        if (cache == null) {
            return null;
        }
        return cache.getCache();
    }

    @PostMapping(path = "/generateAllCarsDataSeparatedCache")
    public String generateAllCarsDataSeparatedCache(@RequestBody BaseReq req,
            HttpServletRequest request) {

        CarAllDataRes res = new CarAllDataRes();

        List<Brand> brands = brandRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        BrandDto[] brandDtos = new BrandDto[brands.size()];
        int cntr = 0;
        for (Brand b : brands) {
            BrandDto brandDto = new BrandDto();
            brandDto.fromEntity(b);
            List<CarModel> carModels = carModelRepository.findByIsAliveAndIsActiveAndBrand(Boolean.TRUE, Boolean.TRUE, b);
            CarModelDto[] carModelDtos = new CarModelDto[carModels.size()];
            int cntr2 = 0;
            for (CarModel cm : carModels) {
                CarModelDto carModelDto = new CarModelDto();
                carModelDto.fromEntity(cm);

                List<CarType> carTypes = carTypeRepository.findByIsAliveAndIsActiveAndCarModel(Boolean.TRUE, Boolean.TRUE, cm);
                CarTypeDto[] carTypeDtos = new CarTypeDto[carTypes.size()];
                int cntr3 = 0;
                for (CarType ct : carTypes) {
                    CarTypeDto carTypeDto = new CarTypeDto();
                    carTypeDto.fromEntity(ct);
                    carTypeDtos[cntr3++] = carTypeDto;
                }
                carModelDto.setCarTypes(carTypeDtos);
                carModelDtos[cntr2++] = carModelDto;
            }
            brandDtos[cntr++] = brandDto;
            brandDto.setCarModels(carModelDtos);
        }
        res.setBrands(brandDtos);

        List<CarBody> bodies = carBodyRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarBodyDto[] carBodyDtos = new CarBodyDto[bodies.size()];
        int cntr1 = 0;
        for (CarBody b : bodies) {
            CarBodyDto bodyDto = new CarBodyDto();
            bodyDto.fromEntity(b);
            carBodyDtos[cntr1++] = bodyDto;
        }
        res.setBodies(carBodyDtos);

        List<CarColor> colors = carColorRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarColorDto[] carColorDtos = new CarColorDto[colors.size()];
        int cntr2 = 0;
        for (CarColor b : colors) {
            CarColorDto colorDto = new CarColorDto();
            colorDto.fromEntity(b);
            carColorDtos[cntr2++] = colorDto;
        }
        res.setColors(carColorDtos);

        List<CarBodyStatus> bodyStatuses = carBodyStatusRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        BodyStatusDto[] bodyStatusDtos = new BodyStatusDto[bodyStatuses.size()];
        int cntr3 = 0;
        for (CarBodyStatus b : bodyStatuses) {
            BodyStatusDto bodyStatusDto = new BodyStatusDto();
            bodyStatusDto.fromEntity(b);
            bodyStatusDtos[cntr3++] = bodyStatusDto;
        }
        res.setBodyStatuses(bodyStatusDtos);

        List<CarGear> gears = carGearRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarGearDto[] carGearDtos = new CarGearDto[gears.size()];
        int cntr4 = 0;
        for (CarGear b : gears) {
            CarGearDto carGearDto = new CarGearDto();
            carGearDto.fromEntity(b);
            carGearDtos[cntr4++] = carGearDto;
        }
        res.setGears(carGearDtos);

        List<CarFuel> fuels = carFuelRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarFuelDto[] carFuelDtos = new CarFuelDto[fuels.size()];
        int cntr5 = 0;
        for (CarFuel b : fuels) {
            CarFuelDto carFuelDto = new CarFuelDto();
            carFuelDto.fromEntity(b);
            carFuelDtos[cntr5++] = carFuelDto;
        }
        res.setFuels(carFuelDtos);

        List<CarCylinder> cylinders = carCylinderRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarCylinderDto[] carCylinderDtos = new CarCylinderDto[cylinders.size()];
        int cntr6 = 0;
        for (CarCylinder b : cylinders) {
            CarCylinderDto carCylinderDto = new CarCylinderDto();
            carCylinderDto.fromEntity(b);
            carCylinderDtos[cntr6++] = carCylinderDto;
        }
        res.setCylinders(carCylinderDtos);

        List<CarDifferential> differentials = carDifferentialRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarDifferentialDto[] carDifferentialDtos = new CarDifferentialDto[differentials.size()];
        int cntr7 = 0;
        for (CarDifferential b : differentials) {
            CarDifferentialDto carDifferentialDto = new CarDifferentialDto();
            carDifferentialDto.fromEntity(b);
            carDifferentialDtos[cntr7++] = carDifferentialDto;
        }
        res.setDifferentials(carDifferentialDtos);

        List<CarEngine> engines = carEngineRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarEngineDto[] carEngineDtos = new CarEngineDto[engines.size()];
        int cntr8 = 0;
        for (CarEngine b : engines) {
            CarEngineDto carEngineDto = new CarEngineDto();
            carEngineDto.fromEntity(b);
            carEngineDtos[cntr8++] = carEngineDto;
        }
        res.setEngines(carEngineDtos);

        List<CarTrimColor> trimColors = carTrimColorRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarTrimColorDto[] trimColorDtos = new CarTrimColorDto[trimColors.size()];
        int cntr9 = 0;
        for (CarTrimColor b : trimColors) {
            CarTrimColorDto carTrimColorDto = new CarTrimColorDto();
            carTrimColorDto.fromEntity(b);
            trimColorDtos[cntr9++] = carTrimColorDto;
        }
        res.setTrimColors(trimColorDtos);

        List<CarOrigin> origins = carOriginRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        CarOriginDto[] originDtos = new CarOriginDto[origins.size()];
        int cntr10 = 0;
        for (CarOrigin b : origins) {
            CarOriginDto carOriginDto = new CarOriginDto();
            carOriginDto.fromEntity(b);
            originDtos[cntr10++] = carOriginDto;
        }
        res.setOrigins(originDtos);

        List<ManufactureYear> manufactureYears = manufactureYearRepository.findByIsAliveAndIsActive(Boolean.TRUE, Boolean.TRUE);
        ManufactureYearDto[] manufactureYearDtos = new ManufactureYearDto[manufactureYears.size()];
        int cntr11 = 0;
        for (ManufactureYear b : manufactureYears) {
            ManufactureYearDto manufactureYearDto = new ManufactureYearDto();
            manufactureYearDto.fromEntity(b);
            manufactureYearDtos[cntr11++] = manufactureYearDto;
        }
        res.setManufactureYears(manufactureYearDtos);

        Cache cache = cacheService.controlAndSave(CacheEnm.SPRT, gson.toJson(res));
        if (cache == null) {
            return null;
        }
        return cache.getCache();
    }

    @PostMapping(path = "/getAllCars")
    public AllCarsRes getAllCars(@RequestBody BaseReq req,
            HttpServletRequest request) {

        Cache found = cacheRepository.findByTypeAndIsAliveAndIsActive(CacheEnm.CR, Boolean.TRUE, Boolean.TRUE);
        AllCarsRes res = gson.fromJson(found.getCache(), AllCarsRes.class);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getAllCarsDataSeparatedCache")
    public CarAllDataRes getAllCarsDataSeparatedCache(@RequestBody BaseReq req,
            HttpServletRequest request) {

        Cache found = cacheRepository.findByTypeAndIsAliveAndIsActive(CacheEnm.SPRT, Boolean.TRUE, Boolean.TRUE);
        CarAllDataRes res = gson.fromJson(found.getCache(), CarAllDataRes.class);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getBodyStatuses")
    public BodyStatusesRes getBodyStatuses(@RequestBody BaseReq req,
            HttpServletRequest request) {
        BodyStatusesRes res = new BodyStatusesRes();
        List<CarBodyStatus> bodies = carBodyStatusRepository.findByIsActiveAndIsAlive(Boolean.TRUE, Boolean.TRUE);

        if (bodies == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        BodyStatusDto[] bodyStatusDtos = new BodyStatusDto[bodies.size()];
        int cntr = 0;
        for (CarBodyStatus user : bodies) {
            BodyStatusDto bodyStatusDto = new BodyStatusDto();
            bodyStatusDto.fromEntity(user);
            bodyStatusDtos[cntr++] = bodyStatusDto;
        }

        res.setCount(String.valueOf(bodies.size()));
        res.setBodyStatuses(bodyStatusDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getProvinces")
    public RegionsRes getProvinces(@RequestBody BaseReq req,
            HttpServletRequest request) {
        RegionsRes res = new RegionsRes();
        List<Region> regions = regionRepository.findByTypeAndIsActiveAndIsAlive(RegionTypeEnm.PRV, Boolean.TRUE, Boolean.TRUE);

        if (regions == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        RegionDto[] regionDtos = new RegionDto[regions.size()];
        int cntr = 0;
        for (Region region : regions) {
            RegionDto regionDto = new RegionDto();
            regionDto.fromEntity(region);
            regionDtos[cntr++] = regionDto;
        }

        res.setCount(String.valueOf(regions.size()));
        res.setRegions(regionDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getCities")
    public RegionsRes getCities(@RequestBody RegionReq req,
            HttpServletRequest request) {
        RegionsRes res = new RegionsRes();
        if (req == null || ValidationUtil.isEmpty(req.getParentId())) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }
        Optional<Region> parent = regionRepository.findById(Long.valueOf(req.getParentId()));
        List<Region> regions = regionRepository.findByTypeAndParentAndIsActiveAndIsAlive(RegionTypeEnm.CTY, parent.get(), Boolean.TRUE, Boolean.TRUE);

        if (regions == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        RegionDto[] regionDtos = new RegionDto[regions.size()];
        int cntr = 0;
        for (Region region : regions) {
            RegionDto regionDto = new RegionDto();
            regionDto.fromEntity(region);
            regionDtos[cntr++] = regionDto;
        }

        res.setCount(String.valueOf(regions.size()));
        res.setRegions(regionDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getRegions")
    public RegionsRes getRegions(@RequestBody RegionReq req,
            HttpServletRequest request) {
        RegionsRes res = new RegionsRes();
        if (req == null || ValidationUtil.isEmpty(req.getParentId())) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }
        Optional<Region> parent = regionRepository.findById(Long.valueOf(req.getParentId()));
        List<Region> regions = regionRepository.findByTypeAndParentAndIsActiveAndIsAlive(RegionTypeEnm.RGN, parent.get(), Boolean.TRUE, Boolean.TRUE);

        if (regions == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        RegionDto[] regionDtos = new RegionDto[regions.size()];
        int cntr = 0;
        for (Region region : regions) {
            RegionDto regionDto = new RegionDto();
            regionDto.fromEntity(region);
            regionDtos[cntr++] = regionDto;
        }

        res.setCount(String.valueOf(regions.size()));
        res.setRegions(regionDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getRules1")
    public RuleRes getRules1(@RequestBody BaseReq req,
            HttpServletRequest request) {
        RuleRes res = new RuleRes();
        List<Rule> found;
        found = ruleService.getRules1();
        RuleDto RuleDto = new RuleDto();
        RuleDto.fromEntity(found.get(0));
        res.setRule(RuleDto);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getRules2")
    public RuleRes getRules2(@RequestBody BaseReq req,
            HttpServletRequest request) {
        RuleRes res = new RuleRes();
        List<Rule> found;
        found = ruleService.getRules2();
        RuleDto RuleDto = new RuleDto();
        RuleDto.fromEntity(found.get(0));
        res.setRule(RuleDto);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getRules3")
    public RuleRes getRules3(@RequestBody BaseReq req,
            HttpServletRequest request) {
        RuleRes res = new RuleRes();
        List<Rule> found;
        found = ruleService.getRules3();
        RuleDto RuleDto = new RuleDto();
        RuleDto.fromEntity(found.get(0));
        res.setRule(RuleDto);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getAboutUsContent")
    public RuleRes getAboutUsContent(@RequestBody BaseReq req,
            HttpServletRequest request) {
        RuleRes res = new RuleRes();
        List<Rule> found;
        found = ruleService.getAboutUs();
        RuleDto RuleDto = new RuleDto();
        RuleDto.fromEntity(found.get(0));
        res.setRule(RuleDto);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getContactUs")
    public RuleRes getContactUs(@RequestBody BaseReq req,
            HttpServletRequest request) {
        RuleRes res = new RuleRes();
        List<Rule> found;
        found = ruleService.getContactUs();
        RuleDto RuleDto = new RuleDto();
        RuleDto.fromEntity(found.get(0));
        res.setRule(RuleDto);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getManaPrices")
    public ManaPriceRes getManaPrices(@RequestBody BaseListReq req,
            HttpServletRequest request) {

        ManaPriceRes res = new ManaPriceRes();

        ir.aria.pelaksefid.client.consume.sigma.ManaPriceRes manaPriceRes = SigmaServiceClient.getManaPrices(req);

        if (manaPriceRes == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        ManaPriceDto[] manaPriceDtos = new ManaPriceDto[Integer.valueOf(manaPriceRes.getCount())];
        int cntr = 0;
        for (ManaPrice d : manaPriceRes.getManaPrices()) {
            ManaPriceDto advertiseDto = new ManaPriceDto();
            advertiseDto.fromModel(d);
            manaPriceDtos[cntr++] = advertiseDto;
        }
        res.setCount(String.valueOf(manaPriceRes.getCount()));
        res.setManaPrices(manaPriceDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }

    @PostMapping(path = "/getEstgahNews")
    public NewsRes getEstgahNews(@RequestBody BaseListReq req,
            HttpServletRequest request) {

        NewsRes res = new NewsRes();

        ir.aria.pelaksefid.client.consume.woocommerce.NewsRes newsRes = ManaTecServiceClient.getNews(req.getPl(), req.getPn());

        if (newsRes == null) {
            res.setStatus(ResultEnm.GENERAL_ERROR.ordinal());
            res.setMessage(ResultEnm.GENERAL_ERROR.name());
            return res;
        }

        NewDto[] newDtos = new NewDto[newsRes.getNews().length];
        int cntr = 0;
        for (NewRes n : newsRes.getNews()) {
            NewDto news = new NewDto();
            news.setLink(n.getLink());
            news.setTitle(n.getTitle() != null ? n.getTitle().getRendered() : "");
            if (n.getEmbedded() != null && n.getEmbedded().getWpFeaturedmedia() != null && n.getEmbedded().getWpFeaturedmedia().length != 0) {
                news.setImageUrl(n.getEmbedded().getWpFeaturedmedia()[0].getSource_url());
            }
            newDtos[cntr++] = news;
        }
        res.setCount(String.valueOf(newsRes.getCount()));
        res.setNews(newDtos);
        res.setStatus(ResultEnm.OK.ordinal());
        res.setMessage(ResultEnm.OK.name());
        return res;
    }
}

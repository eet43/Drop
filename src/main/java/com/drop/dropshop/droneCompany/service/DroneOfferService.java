package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.DroneOfferDto;
import com.drop.dropshop.droneCompany.entity.CompanyDrone;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import com.drop.dropshop.droneCompany.exception.OpenApiResponseErrorException;
import com.drop.dropshop.droneCompany.repository.DroneCompanyOwnDroneDetailRepository;
import com.drop.dropshop.droneCompany.repository.DroneCompanyOwnDroneRepository;
import com.drop.dropshop.droneCompany.repository.DroneModelRepository;
import com.drop.dropshop.droneCompany.util.NaverGeocode;
import com.drop.dropshop.droneCompany.util.OpenWeatherMap;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DroneOfferService {
    private final DroneCompanyOwnDroneDetailRepository droneCompanyOwnDroneDetailRepository;
    private final DroneCompanyOwnDroneRepository droneCompanyOwnDroneRepository;
    private final DroneModelRepository droneModelRepository;
    private final NaverGeocode naverGeocode;
    private final OpenWeatherMap openWeatherMap;


    /**
     * 조건에 맞는 드론 목록 반환
     */
    public List<DroneOfferDto> show(double weight, String origin, String destination) throws OpenApiResponseErrorException, IOException {
        // 주소를 좌표로 변환
        JSONObject originCoordinate = naverGeocode.geocode(origin);

        // 주소를 좌표로 변환
        JSONObject destinationCoordinate = naverGeocode.geocode(destination);

        int originWeather = (int)openWeatherMap.getWeather(originCoordinate);
        int destinationWeather = (int)openWeatherMap.getWeather(destinationCoordinate);

        int lowest = Math.min(originWeather, destinationWeather) - 1;
        int maximum = Math.max(originWeather, destinationWeather) + 1;

        System.out.println(lowest);
        System.out.println(maximum);

        List<DroneModel> selectableModelList= droneModelRepository.findAllByLowestTemperatureLessThanEqualAndMaximumTemperatureGreaterThanEqualAndFlightWeightGreaterThanEqual(lowest, maximum, weight);

        List<DroneOfferDto> droneOfferDtoList = new ArrayList<>();

        for (DroneModel droneModel : selectableModelList){
            List<CompanyDrone> companyDroneList = droneCompanyOwnDroneRepository.findAllByModelIdAndOperableNumGreaterThanEqual(droneModel.getModelId(), 1);
            System.out.println(companyDroneList);
            droneOfferDtoList.add(new DroneOfferDto(droneModel.getModelId(), droneModel.getModelName(), droneModel.isCamera(), companyDroneList));
            System.out.println(droneOfferDtoList);
        }

        return droneOfferDtoList;
    }
}

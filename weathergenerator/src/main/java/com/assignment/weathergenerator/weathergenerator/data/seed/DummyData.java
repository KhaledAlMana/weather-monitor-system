package com.assignment.weathergenerator.weathergenerator.data.seed;

import java.util.ArrayList;
import java.util.List;
import com.assignment.weathergenerator.weathergenerator.dto.LocationDTO;

public class DummyData {

    // Hardcoded data for testing
    public static ArrayList<LocationDTO> locations = new ArrayList<LocationDTO>((List.of(
            new LocationDTO("Al Rawabi", 24.695801968365906, 46.79263096938105),
            new LocationDTO("Al Murabba", 24.65407170882359, 46.71063786415048),
            new LocationDTO("Al Mathar Ash Shamali", 24.68924400746815, 46.667951258344345),
            new LocationDTO("Al Muruj", 24.75814810320675, 46.66199810996489),
            new LocationDTO("Alyasmin", 24.824442056709536, 46.64696097818352),
            new LocationDTO("Al Malqa", 24.814538397383807, 46.61098064169341),
            new LocationDTO("Al Aqiq", 24.774565428313235, 46.631193762127005),
            new LocationDTO("HITTIN", 24.761866253801355, 46.60381927222157),
            new LocationDTO("Al Khuzama", 24.7200241773241, 46.59975527904584),
            new LocationDTO("An Nada", 24.805906005501882, 46.68435051631271),
            new LocationDTO("As Sahafah", 24.799470125822896, 46.64423987285216))));
}

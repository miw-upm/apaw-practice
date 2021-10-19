package es.upm.miw.apaw_practice.adapters.mongodb.tv_series;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.PlayerSeriesRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.ProducerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.TvSeriesRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.EpisodeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.PlayerSeriesEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.ProducerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.TvSeriesEntity;
import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TvSeriesSeederService {
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private TvSeriesRepository tvSeriesRepository;
    @Autowired
    private PlayerSeriesRepository playerSeriesRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- TvSeries Initial Load -----------");
        ProducerEntity[] producers = {
                new ProducerEntity(new Producer("Ufotable, Inc.","ufotable@anime.com", 123456789L)),
                new ProducerEntity(new Producer("Kodansha, Ltd.","kodansha@anime.com", 111222333L)),
                new ProducerEntity(new Producer("New Line Cinema Inc.","newline@cinema.com", 987654321L)),
                new ProducerEntity(new Producer("A-1 Pictures Inc.","a1@pictures.com", 327468273L)),
        };
        this.producerRepository.saveAll(Arrays.asList(producers));
        List<EpisodeEntity> episodeEntities1 = new ArrayList<>();
        for(int i = 1;i<=25;i++)
            episodeEntities1.add(new EpisodeEntity(i,1,25));
        for(int i = 1;i<=12;i++)
            episodeEntities1.add(new EpisodeEntity(i,2,28));
        List<EpisodeEntity> episodeEntities2 = new ArrayList<>();
        for(int i = 1;i<=24;i++)
            episodeEntities2.add(new EpisodeEntity(i,1,24));
        TvSeriesEntity[] tvSeries = {
                new TvSeriesEntity("Fairy Tail",2009,true,producers[3],episodeEntities1),
                new TvSeriesEntity("Kimetsu No Yaiba",2019,false,producers[0]),
                new TvSeriesEntity("Shingeki No Kyojin",2013,false,producers[1],episodeEntities2),
                new TvSeriesEntity("Vinland Saga",2019,false,producers[1])
        };
        this.tvSeriesRepository.saveAll(Arrays.asList(tvSeries));
        PlayerSeriesEntity[] players = {
                new PlayerSeriesEntity("Yuki Kaji",LocalDate.of(1985,9,3),"Japan",List.of(tvSeries[1],tvSeries[2])),
                new PlayerSeriesEntity("Yuto Uemura",LocalDate.of(1993,10,23),"Japan",List.of(tvSeries[3])),
                new PlayerSeriesEntity("Todd Haberkorn",LocalDate.of(1982,8,16),"United States",List.of(tvSeries[0])),
                new PlayerSeriesEntity("Brittney Karbowski",LocalDate.of(1986,6,26),"United States",List.of(tvSeries[0]))
        };
        this.playerSeriesRepository.saveAll(Arrays.asList(players));
    }

    public void deleteAll() {
        this.producerRepository.deleteAll();
        this.playerSeriesRepository.deleteAll();
        this.tvSeriesRepository.deleteAll();
    }
}

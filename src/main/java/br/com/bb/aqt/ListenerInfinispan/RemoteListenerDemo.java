package br.com.bb.aqt.ListenerInfinispan;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.configuration.ExhaustedAction;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;
import org.infinispan.client.hotrod.impl.ConfigurationProperties;
import org.infinispan.commons.marshall.Marshaller;

import br.com.bb.aqt.ListenerInfinispan.model.Cliente;
import br.com.bb.aqt.ListenerInfinispan.model.MetricaLatenciaModel;

public class RemoteListenerDemo {

   public static void main(String[] args) throws InterruptedException {

      // ConfigurationBuilder builder = new ConfigurationBuilder();
      
      // builder.clientIntelligence(ClientIntelligence.TOPOLOGY_AWARE);

      // builder.addServer()
      //    .host("datagrid.apps.k8sdesbb110.desenv.bb.com.br")
      //    .port(ConfigurationProperties.DEFAULT_HOTROD_PORT)
      //    .clientIntelligence(ClientIntelligence.BASIC)
      //    .security()
      //    .authentication()
      //    .enable()
      //    .username("developer")
      //    .password("JWdZBxalcMmfgXOS")
      //    .saslMechanism("DIGEST-MD5");

      // builder.addServer()
      //       .host("datagrid.apps.k8sdesbb110.desenv.bb.com.br")
      //       .port(ConfigurationProperties.DEFAULT_HOTROD_PORT)
            // .security()
            // .authentication()
            // .saslMechanism("DIGEST-MD5")
            // .username("developer")
            // .password("JWdZBxalcMmfgXOS");


      // builder.addServer()
      //       .host("localhost")
      //       .port(ConfigurationProperties.DEFAULT_HOTROD_PORT)
      //       .security()
      //       .authentication()
      //       .saslMechanism("DIGEST-MD5")
      //       .username("admin")
      //       .password("password");

      // builder.remoteCache("kafka-debezium");
      // builder.remoteCache("cliente");

      // RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());

      // // RemoteCache<Long, MetricaLatenciaModel> cache = cacheManager.getCache("kafka-debezium");
      // RemoteCache<Long, String> cache = cacheManager.getCache("cliente");

      // BasicListener listener = new BasicListener();
      // cache.addClientListener(listener);

      // cache.put(122332123l, "TESTE 123");

      ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                    1, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>()); 

            for (int i = 0; i < 1; i++) {
                executor.execute(() -> {
                  ConfigurationBuilder builder = new ConfigurationBuilder();
      
                  builder.clientIntelligence(ClientIntelligence.TOPOLOGY_AWARE);

                  builder.addServer()
                  .host("localhost")
                  .port(ConfigurationProperties.DEFAULT_HOTROD_PORT)
                  .security()
                  .authentication()
                  .saslMechanism("DIGEST-MD5")
                  .username("admin")
                  .password("password");

                  builder.remoteCache("cliente");

                  RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());
            
                  // RemoteCache<Long, MetricaLatenciaModel> cache = cacheManager.getCache("kafka-debezium");
                  RemoteCache<Long, Cliente> cache = cacheManager.getCache("cliente");
                  // RemoteCache<Long, MetricaLatenciaModel> cache = cacheManager.getCache("cliente");
            
                  BasicListener listener = new BasicListener();
                  cache.addClientListener(listener);
            
                  cache.put(122332123l, new Cliente(123321, "Arthur Alcantara", 123654789l, LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli()));
                  //cache.put(122332123l, "teste teste teste");

                });
            }
   }

   @ClientListener
   public static class BasicListener {

      @ClientCacheEntryCreated
      public void entryCreated(ClientCacheEntryCreatedEvent<String> event) {
         System.out.println(event);
         System.out.printf("[LOG] Criado Elemento, Chave: %s - Timestamp: %s", event.getKey(), LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli());
      }


      @ClientCacheEntryModified
      public void entryModified(ClientCacheEntryModifiedEvent<String> event) {
         System.out.printf("[LOG] Atualizado Elemento, Chave: %s - Timestamp: %s", event.getKey(), LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli());
      }

   }

}
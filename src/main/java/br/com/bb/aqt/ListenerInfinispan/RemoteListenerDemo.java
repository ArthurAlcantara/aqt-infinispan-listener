package br.com.bb.aqt.ListenerInfinispan;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;
import org.infinispan.client.hotrod.impl.ConfigurationProperties;

import br.com.bb.aqt.ListenerInfinispan.model.Cliente;
import br.com.bb.aqt.ListenerInfinispan.model.MetricaLatenciaModel;

public class RemoteListenerDemo {

   public static void main(String[] args) throws InterruptedException {

      ConfigurationBuilder builder = new ConfigurationBuilder();
      
      builder.clientIntelligence(ClientIntelligence.TOPOLOGY_AWARE);

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


      builder.addServer()
            .host("localhost")
            .port(ConfigurationProperties.DEFAULT_HOTROD_PORT)
            .security()
            .authentication()
            .saslMechanism("DIGEST-MD5")
            .username("admin")
            .password("password");

      // builder.remoteCache("kafka-debezium");
      builder.remoteCache("cliente");

      RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());

      // RemoteCache<Long, MetricaLatenciaModel> cache = cacheManager.getCache("kafka-debezium");
      RemoteCache<Long, Cliente> cache = cacheManager.getCache("cliente");

      BasicListener listener = new BasicListener();
      cache.addClientListener(listener);

      // while(true){

      // }

      //cache.put(123546l, new Cliente(123546, "Arthur", 12345678900l, LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli()));

      /*
       * cache.put("entry1", "value1");
       * cache.put("entry2", "value2");
       * cache.put("entry3", "value3");
       */

   }

   @ClientListener
   public static class BasicListener {

      @ClientCacheEntryCreated
      public void entryCreated(ClientCacheEntryCreatedEvent<String> event) {
         System.out.printf("Created %s%n", event.getKey());
      }

      @ClientCacheEntryModified
      public void entryModified(ClientCacheEntryModifiedEvent<String> event) {
         System.out.printf("Going to modify %s%n", event.getKey());
      }

   }

}
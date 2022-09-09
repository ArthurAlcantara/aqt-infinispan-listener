package br.com.bb.aqt.ListenerInfinispan.model;

import java.util.Objects;
import java.util.Set;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;
// import org.jboss.logging.Logger;


@ProtoDoc("@Indexed")
public class Cliente {

   private Integer cd_cli;
   private String nm_cli;
   private Long nr_cpf;
   private Long ts_atl;

   @ProtoFactory
   public Cliente(Integer cd_cli, String nm_cli, Long nr_cpf, Long ts_atl) {
      this.cd_cli = Objects.requireNonNull(cd_cli);
      this.nm_cli = Objects.requireNonNull(nm_cli);
      this.nr_cpf = Objects.requireNonNull(nr_cpf);
      this.ts_atl = Objects.requireNonNull(ts_atl);
   }

   // @Override
   // protected void finalize(){
   //    LOGGER.warn("terminando.. "+this.cd_cli);
   // }
   public Cliente() {
   }

   @ProtoField(1)
   public Integer getCd_cli() {
      return cd_cli;
   }

   public void setCd_cli(Integer cd_cli) {
      this.cd_cli = cd_cli;
   }

   @ProtoField(2)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.YES, store = Store.YES)")

   public String getNm_cli() {
      return nm_cli;
   }

   public void setNm_cli(String nm_cli) {
      this.nm_cli = nm_cli;
   }

   @ProtoField(3)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.YES)")

   public Long getNr_cpf() {
      return nr_cpf;
   }

   public void setNr_cpf(Long nr_cpf) {
      this.nr_cpf = nr_cpf;
   }

   @ProtoField(4)
   // @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.YES)")
   public Long getTs_atl() {
      return ts_atl;
   }

   public void setTs_atl(Long ts_atl) {
      this.ts_atl = ts_atl;
   }

   @Override
   public String toString() {
      return "ClienteCDC [cd_cli=" + cd_cli + ", nm_cli=" + nm_cli + ", nr_cpf=" + nr_cpf
            + ", ts_atl=" + ts_atl + "]";
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Cliente that = (Cliente) o;
      return Objects.equals(cd_cli, that.cd_cli) && Objects.equals(nm_cli, that.nm_cli)
            && Objects.equals(nr_cpf, that.nr_cpf) && Objects.equals(ts_atl, that.ts_atl);
   }

   @Override
   public int hashCode() {
      return Objects.hash(cd_cli, nm_cli, nr_cpf, ts_atl);
   }

}

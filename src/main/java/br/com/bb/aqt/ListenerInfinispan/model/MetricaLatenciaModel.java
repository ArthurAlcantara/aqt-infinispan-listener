package br.com.bb.aqt.ListenerInfinispan.model;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;


@ProtoDoc("@Indexed")
public class MetricaLatenciaModel {
    
    Long DT_CRIACAO;
    Long DT_PROCESSAMENTO;

    public MetricaLatenciaModel(){}

    @ProtoFactory
    public MetricaLatenciaModel(Long dT_CRIACAO, Long dT_PROCESSAMENTO) {
        DT_CRIACAO = dT_CRIACAO;
        DT_PROCESSAMENTO = dT_PROCESSAMENTO;
    }

    @ProtoField(1)
    public Long getDT_CRIACAO() {
        return DT_CRIACAO;
    }

    public void setDT_CRIACAO(Long dT_CRIACAO) {
        DT_CRIACAO = dT_CRIACAO;
    }

    @ProtoField(2)
    public Long getDT_PROCESSAMENTO() {
        return DT_PROCESSAMENTO;
    }

    public void setDT_PROCESSAMENTO(Long dT_PROCESSAMENTO) {
        DT_PROCESSAMENTO = dT_PROCESSAMENTO;
    }

    @Override
    public String toString() {
        return "MetricaLatenciaModel [DT_CRIACAO=" + DT_CRIACAO + ", DT_PROCESSAMENTO=" + DT_PROCESSAMENTO + "]";
    }
}

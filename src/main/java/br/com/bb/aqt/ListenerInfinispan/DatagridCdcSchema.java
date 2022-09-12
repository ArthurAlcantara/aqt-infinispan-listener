package br.com.bb.aqt.ListenerInfinispan;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

import br.com.bb.aqt.ListenerInfinispan.model.Cliente;

@AutoProtoSchemaBuilder(includeClasses = { Cliente.class}, schemaPackageName = "CDC")
public interface DatagridCdcSchema extends GeneratedSchema {
}

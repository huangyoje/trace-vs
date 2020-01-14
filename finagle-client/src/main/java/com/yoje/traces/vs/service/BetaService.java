/**
 * Generated by Scrooge
 *   version: 4.5.0
 *   rev: 014664de600267b36809bbc85225e26aec286216
 *   built at: 20160203-205352
 */
package com.yoje.traces.vs.service;

import com.twitter.scrooge.Option;
import com.twitter.scrooge.ThriftStruct;
import com.twitter.scrooge.ThriftStructCodec;
import com.twitter.scrooge.ThriftStructCodec3;
import com.twitter.scrooge.Utilities;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import org.apache.thrift.protocol.*;
import org.apache.thrift.TApplicationException;
import com.twitter.finagle.SourcedException;
import com.twitter.finagle.stats.Counter;
import com.twitter.finagle.stats.NullStatsReceiver;
import com.twitter.finagle.stats.StatsReceiver;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.util.Function2;
import com.twitter.util.Function;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;
import java.util.Arrays;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TTransport;


@javax.annotation.Generated(value = "com.twitter.scrooge.Compiler")
public class BetaService {
  public static interface Iface {
    
    public FinagleResponse invoke(FinagleRequest request);
  }

  public static interface FutureIface {
    
    public Future<FinagleResponse> invoke(FinagleRequest request);
  }

  public static class FinagledClient extends BetaService$FinagleClient {
    public FinagledClient (
      com.twitter.finagle.Service<com.twitter.finagle.thrift.ThriftClientRequest, byte[]> service,
      TProtocolFactory protocolFactory,
      String serviceName,
      com.twitter.finagle.stats.StatsReceiver stats
      ) {
        super(service, protocolFactory, serviceName, stats);
      }
  }

  public static class FinagledService extends BetaService$FinagleService {
    public FinagledService (
      FutureIface iface,
      TProtocolFactory protocolFactory
      ) {
        super(iface, protocolFactory);
    }
  }


  public static class Invoke {
  static class Args implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("invoke_args");
    private static final TField RequestField = new TField("request", TType.STRUCT, (short) -1);
    final FinagleRequest request;
  
    public static class Builder {
      private FinagleRequest _request = null;
      private Boolean _got_request = false;
  
      public Builder request(FinagleRequest value) {
        this._request = value;
        this._got_request = true;
        return this;
      }
  
      public Builder unsetRequest() {
        this._request = null;
        this._got_request = false;
        return this;
      }
  
      public Args build() {
        return new Args(
          this._request    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      builder.request(this.request);
      return builder;
    }
  
    public static ThriftStructCodec<Args> CODEC = new ThriftStructCodec3<Args>() {
      @java.lang.Override
      public Args decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        FinagleRequest request = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case -1: /* request */
                switch (_field.type) {
                  case TType.STRUCT:
                    FinagleRequest request_item;
                    request_item = com.yoje.traces.vs.service.FinagleRequest.decode(_iprot);
                    request = request_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.request(request);
                break;
              default:
                TProtocolUtil.skip(_iprot, _field.type);
            }
            _iprot.readFieldEnd();
          }
        }
        _iprot.readStructEnd();
        try {
          return builder.build();
        } catch (IllegalStateException stateEx) {
          throw new TProtocolException(stateEx.getMessage());
        }
      }
  
      @java.lang.Override
      public void encode(Args struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static Args decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(Args struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public Args(
      FinagleRequest request
    ) {
      this.request = request;
    }
  
  
    public FinagleRequest getRequest() {
      return this.request;
    }
    public boolean isSetRequest() {
      return this.request != null;
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
        _oprot.writeFieldBegin(RequestField);
        com.yoje.traces.vs.service.FinagleRequest request_item = request;
        request_item.write(_oprot);
        _oprot.writeFieldEnd();
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
  
    @java.lang.Override
    public boolean equals(Object other) {
      if (!(other instanceof Args)) return false;
      Args that = (Args) other;
      return
  this.request.equals(that.request);
    }
  
    @java.lang.Override
    public String toString() {
      return "Args(" + this.request + ")";
    }
  
    @java.lang.Override
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.request == null ? 0 : this.request.hashCode());
      return hash;
    }
  }
  static class Result implements ThriftStruct {
    private static final TStruct STRUCT = new TStruct("invoke_result");
    private static final TField SuccessField = new TField("success", TType.STRUCT, (short) 0);
    final Option<FinagleResponse> success;
  
    public static class Builder {
      private FinagleResponse _success = null;
      private Boolean _got_success = false;
  
      public Builder success(FinagleResponse value) {
        this._success = value;
        this._got_success = true;
        return this;
      }
  
      public Builder unsetSuccess() {
        this._success = null;
        this._got_success = false;
        return this;
      }
  
      public Result build() {
        return new Result(
        Option.make(this._got_success, this._success)    );
      }
    }
  
    public Builder copy() {
      Builder builder = new Builder();
      if (this.success.isDefined()) builder.success(this.success.get());
      return builder;
    }
  
    public static ThriftStructCodec<Result> CODEC = new ThriftStructCodec3<Result>() {
      @java.lang.Override
      public Result decode(TProtocol _iprot) throws org.apache.thrift.TException {
        Builder builder = new Builder();
        FinagleResponse success = null;
        Boolean _done = false;
        _iprot.readStructBegin();
        while (!_done) {
          TField _field = _iprot.readFieldBegin();
          if (_field.type == TType.STOP) {
            _done = true;
          } else {
            switch (_field.id) {
              case 0: /* success */
                switch (_field.type) {
                  case TType.STRUCT:
                    FinagleResponse success_item;
                    success_item = com.yoje.traces.vs.service.FinagleResponse.decode(_iprot);
                    success = success_item;
                    break;
                  default:
                    TProtocolUtil.skip(_iprot, _field.type);
                }
                builder.success(success);
                break;
              default:
                TProtocolUtil.skip(_iprot, _field.type);
            }
            _iprot.readFieldEnd();
          }
        }
        _iprot.readStructEnd();
        try {
          return builder.build();
        } catch (IllegalStateException stateEx) {
          throw new TProtocolException(stateEx.getMessage());
        }
      }
  
      @java.lang.Override
      public void encode(Result struct, TProtocol oprot) throws org.apache.thrift.TException {
        struct.write(oprot);
      }
    };
  
    public static Result decode(TProtocol _iprot) throws org.apache.thrift.TException {
      return CODEC.decode(_iprot);
    }
  
    public static void encode(Result struct, TProtocol oprot) throws org.apache.thrift.TException {
      CODEC.encode(struct, oprot);
    }
  
    public Result(
      Option<FinagleResponse> success
    ) {
      this.success = success;
    }
  
  
    public FinagleResponse getSuccess() {
      return this.success.get();
    }
    public boolean isSetSuccess() {
      return this.success.isDefined();
    }
  
    public void write(TProtocol _oprot) throws org.apache.thrift.TException {
      validate();
      _oprot.writeStructBegin(STRUCT);
      if (success.isDefined()) {  _oprot.writeFieldBegin(SuccessField);
        com.yoje.traces.vs.service.FinagleResponse success_item = success.get();
        success_item.write(_oprot);
        _oprot.writeFieldEnd();
      }
      _oprot.writeFieldStop();
      _oprot.writeStructEnd();
    }
  
    private void validate() throws org.apache.thrift.protocol.TProtocolException {
    }
  
  
    @java.lang.Override
    public boolean equals(Object other) {
      if (!(other instanceof Result)) return false;
      Result that = (Result) other;
      return
  this.success.equals(that.success);
    }
  
    @java.lang.Override
    public String toString() {
      return "Result(" + this.success + ")";
    }
  
    @java.lang.Override
    public int hashCode() {
      int hash = 1;
      hash = hash * (this.success.isDefined() ? 0 : this.success.get().hashCode());
      return hash;
    }
  }
  }
  
  
}
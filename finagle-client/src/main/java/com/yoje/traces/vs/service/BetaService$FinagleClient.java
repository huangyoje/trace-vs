/**
 * Generated by Scrooge
 *   version: 4.5.0
 *   rev: 014664de600267b36809bbc85225e26aec286216
 *   built at: 20160203-205352
 */
package com.yoje.traces.vs.service;

import com.twitter.finagle.SourcedException;
import com.twitter.finagle.stats.Counter;
import com.twitter.finagle.stats.StatsReceiver;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.util.Function;
import com.twitter.util.Function2;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;
import com.twitter.scrooge.Option;
import com.twitter.scrooge.ThriftStruct;
import com.twitter.scrooge.ThriftStructCodec;
import com.twitter.scrooge.ThriftStructCodec3;
import com.twitter.scrooge.Utilities;

import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TTransport;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yoje.traces.vs.service.BetaService.*;


public class BetaService$FinagleClient implements BetaService.FutureIface {
  private com.twitter.finagle.Service<ThriftClientRequest, byte[]> service;
  private String serviceName;
  private TProtocolFactory protocolFactory /* new TBinaryProtocol.Factory */;
  private StatsReceiver scopedStats;

  public BetaService$FinagleClient(
    com.twitter.finagle.Service<ThriftClientRequest, byte[]> service,
    TProtocolFactory protocolFactory /* new TBinaryProtocol.Factory */,
    String serviceName,
    StatsReceiver stats
  ) {
    this.service = service;
    this.serviceName = serviceName;
    this.protocolFactory = protocolFactory;
    if (serviceName != "") {
      this.scopedStats = stats.scope(serviceName);
    } else {
      this.scopedStats = stats;
    }
  }

  // ----- boilerplate that should eventually be moved into finagle:

  protected ThriftClientRequest encodeRequest(String name, ThriftStruct args) {
    TMemoryBuffer buf = new TMemoryBuffer(512);
    TProtocol oprot = protocolFactory.getProtocol(buf);

    try {
      oprot.writeMessageBegin(new TMessage(name, TMessageType.CALL, 0));
      args.write(oprot);
      oprot.writeMessageEnd();
    } catch (TException e) {
      // not real.
    }

    byte[] bytes = Arrays.copyOfRange(buf.getArray(), 0, buf.length());
    return new ThriftClientRequest(bytes, false);
  }

  protected <T extends ThriftStruct> T decodeResponse(byte[] resBytes, ThriftStructCodec<T> codec) throws TException {
    TProtocol iprot = protocolFactory.getProtocol(new TMemoryInputTransport(resBytes));
    TMessage msg = iprot.readMessageBegin();
    try {
      if (msg.type == TMessageType.EXCEPTION) {
        TException exception = TApplicationException.read(iprot);
        if (exception instanceof SourcedException) {
          if (this.serviceName != "") ((SourcedException) exception).serviceName_$eq(BetaService$FinagleClient.this.serviceName);
        }
        throw exception;
      } else {
        return codec.decode(iprot);
      }
    } finally {
      iprot.readMessageEnd();
    }
  }

  protected Exception missingResult(String name) {
    return new TApplicationException(
      TApplicationException.MISSING_RESULT,
      "`" + name + "` failed: unknown result"
    );
  }

  protected class __Stats {
    public Counter requestsCounter, successCounter, failuresCounter;
    public StatsReceiver failuresScope;

    public __Stats(String name) {
      StatsReceiver scope = BetaService$FinagleClient.this.scopedStats.scope(name);
      this.requestsCounter = scope.counter0("requests");
      this.successCounter = scope.counter0("success");
      this.failuresCounter = scope.counter0("failures");
      this.failuresScope = scope.scope("failures");
    }
  }

  // ----- end boilerplate.

  private __Stats ___stats_invoke;
  
  private __Stats __stats_invoke() {
    if (___stats_invoke == null) {
      ___stats_invoke = new __Stats("invoke");
    }
    return ___stats_invoke;
  }
  
  
  public Future<FinagleResponse> invoke(FinagleRequest request) {
    __stats_invoke().requestsCounter.incr();
  
    Future<FinagleResponse> rv = this.service.apply(encodeRequest("invoke", new Invoke.Args(request))).flatMap(new Function<byte[], Future<FinagleResponse>>() {
  
      public Future<FinagleResponse> apply(byte[] in) {
        try {
          Invoke.Result result = decodeResponse(in, Invoke.Result.CODEC);
  
  
  
          if (result.success.isDefined()) return Future.value(result.success.get());
          return Future.exception(missingResult("invoke"));
        } catch (TException e) {
          return Future.exception(e);
        }
      }
    }).rescue(new Function<Throwable, Future<FinagleResponse>>() {
      public Future<FinagleResponse> apply(Throwable t) {
        if (t instanceof SourcedException) {
          ((SourcedException) t).serviceName_$eq(BetaService$FinagleClient.this.serviceName);
        }
        return Future.exception(t);
      }
    });
  
    rv.addEventListener(new FutureEventListener<FinagleResponse>() {
      public void onSuccess(FinagleResponse result) {
        __stats_invoke().successCounter.incr();
      }
  
      public void onFailure(Throwable t) {
        if (t instanceof SourcedException) {
          ((SourcedException) t).serviceName_$eq(BetaService$FinagleClient.this.serviceName);
        }
        __stats_invoke().failuresCounter.incr();
        __stats_invoke().failuresScope.counter0(t.getClass().getName()).incr();
      }
    });
  
    return rv;
  }
}
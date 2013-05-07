package com.profiler.common.bo;

import java.util.ArrayList;
import java.util.List;

import com.profiler.common.ServiceType;
import com.profiler.common.dto2.thrift.AgentKey;
import com.profiler.common.dto2.thrift.Annotation;
import com.profiler.common.dto2.thrift.SpanChunk;
import com.profiler.common.buffer.Buffer;
import com.profiler.common.dto2.thrift.SpanEvent;
import com.profiler.common.util.BytesUtils;
import com.profiler.common.buffer.FixedBuffer;

/**
 *
 */
public class SpanEventBo implements Span {
	private static final int VERSION_SIZE = 1;
	// version 0 = prefix의 사이즈를 int로

	private byte version = 0;

	// private static final int MOSTTRACEID = 8;
	// private static final int LEASTTRACEID = 8;
	// private static final int SPANID = 4;
	private static final int PARENTSPANID = 4;


	// private static final int TIMESTAMP = 8;
	private static final int SERVICETYPE = 2;
	private static final int FLAG = 2;
    private static final int AGENTSTARTTIME = 8;

	private String agentId;
    private String applicationId;
    private long agentStartTime;

	private long mostTraceId;
	private long leastTraceId;

	private int spanId;
	private short sequence;

	private int startElapsed;
	private int endElapsed;

	private String rpc;
	private ServiceType serviceType;

    private String destinationId;
    private List<String> destinationAddress;
	private String endPoint;

	private List<AnnotationBo> annotationBoList;

	private int depth = -1;
	private int nextSpanId = -1;

	public SpanEventBo() {
	}

	public SpanEventBo(com.profiler.common.dto2.thrift.Span tSpan, SpanEvent tSpanEvent) {
		this.agentId = tSpan.getAgentId();
        this.applicationId = tSpan.getApplicationName();
        this.agentStartTime = tSpan.getAgentStartTime();

		this.mostTraceId = tSpan.getMostTraceId();
		this.leastTraceId = tSpan.getLeastTraceId();

		this.spanId = tSpan.getSpanId();
		this.sequence = tSpanEvent.getSequence();

		this.startElapsed = tSpanEvent.getStartElapsed();
		this.endElapsed = tSpanEvent.getEndElapsed();

		this.rpc = tSpanEvent.getRpc();
		this.serviceType = ServiceType.findServiceType(tSpanEvent.getServiceType());


        this.destinationId = tSpanEvent.getDestinationId();
        this.destinationAddress = tSpanEvent.getDestinationAddress();

        this.endPoint = tSpanEvent.getEndPoint();
		
		if (tSpanEvent.isSetDepth()) {
			this.depth = tSpanEvent.getDepth();
		}
        
		if (tSpanEvent.isSetNextSpanId()) {
			this.nextSpanId = tSpanEvent.getNextSpanId();
		}
        
		setAnnotationBoList(tSpanEvent.getAnnotations());
	}

	public SpanEventBo(SpanChunk spanChunk, SpanEvent spanEvent) {
		this.agentId = spanChunk.getAgentId();
        this.applicationId = spanChunk.getApplicationName();
        this.agentStartTime = spanChunk.getAgentStartTime();

		this.mostTraceId = spanChunk.getMostTraceId();
		this.leastTraceId = spanChunk.getLeastTraceId();

		this.spanId = spanChunk.getSpanId();
		this.sequence = spanEvent.getSequence();

		this.startElapsed = spanEvent.getStartElapsed();
		this.endElapsed = spanEvent.getEndElapsed();

		this.rpc = spanEvent.getRpc();
		this.serviceType = ServiceType.findServiceType(spanEvent.getServiceType());

        this.destinationId = spanEvent.getDestinationId();
        this.destinationAddress = spanEvent.getDestinationAddress();

		this.endPoint = spanEvent.getEndPoint();
		
		if (spanEvent.isSetDepth()) {
			this.depth = spanEvent.getDepth();
		}

		if (spanEvent.isSetNextSpanId()) {
			this.nextSpanId = spanEvent.getNextSpanId();
		}
		
		setAnnotationBoList(spanEvent.getAnnotations());
	}

	public SpanEventBo(SpanEvent spanEvent) {
        final AgentKey agentKey = spanEvent.getAgentKey();
        if (agentKey != null) {
            this.agentId = agentKey.getAgentId();
            this.applicationId = agentKey.getApplicationName();
            this.agentStartTime = agentKey.getAgentStartTime();
        }

		this.mostTraceId = spanEvent.getMostTraceId();
		this.leastTraceId = spanEvent.getLeastTraceId();

		this.spanId = spanEvent.getSpanId();
		this.sequence = spanEvent.getSequence();

		this.startElapsed = spanEvent.getStartElapsed();
		this.endElapsed = spanEvent.getEndElapsed();

		this.rpc = spanEvent.getRpc();
		this.serviceType = ServiceType.findServiceType(spanEvent.getServiceType());

		this.endPoint = spanEvent.getEndPoint();

        this.destinationId = spanEvent.getDestinationId();
        this.destinationAddress = spanEvent.getDestinationAddress();

		
		if (spanEvent.isSetDepth()) {
			this.depth = spanEvent.getDepth();
		}

		if (spanEvent.isSetNextSpanId()) {
			this.nextSpanId = spanEvent.getNextSpanId();
		}
		
		setAnnotationBoList(spanEvent.getAnnotations());
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

    public long getAgentStartTime() {
        return this.agentStartTime;
    }

    public void setAgentStartTime(long agentStartTime) {
        this.agentStartTime = agentStartTime;
    }

    public long getMostTraceId() {
		return mostTraceId;
	}

	public void setMostTraceId(long mostTraceId) {
		this.mostTraceId = mostTraceId;
	}

	public long getLeastTraceId() {
		return leastTraceId;
	}

	public void setLeastTraceId(long leastTraceId) {
		this.leastTraceId = leastTraceId;
	}

	public void setSpanId(int spanId) {
		this.spanId = spanId;
	}

	public int getSpanId() {
		return this.spanId;
	}

	public short getSequence() {
		return sequence;
	}

	public void setSequence(short sequence) {
		this.sequence = sequence;
	}

	public int getStartElapsed() {
		return startElapsed;
	}

	public void setStartElapsed(int startElapsed) {
		this.startElapsed = startElapsed;
	}

	public int getEndElapsed() {
		return endElapsed;
	}

	public void setEndElapsed(int endElapsed) {
		this.endElapsed = endElapsed;
	}

	public String getRpc() {
		return rpc;
	}

	public void setRpc(String rpc) {
		this.rpc = rpc;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public List<String> getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(List<String> destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public List<AnnotationBo> getAnnotationBoList() {
		return annotationBoList;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getNextSpanId() {
		return nextSpanId;
	}

	public void setNextSpanId(int nextSpanId) {
		this.nextSpanId = nextSpanId;
	}

	private void setAnnotationBoList(List<Annotation> annotations) {
		List<AnnotationBo> boList = new ArrayList<AnnotationBo>(annotations.size());
		for (Annotation ano : annotations) {
			boList.add(new AnnotationBo(ano));
		}
		this.annotationBoList = boList;
	}

	private int getBufferLength(int a, int b, int c, int d, int f) {
		int size = a + b + c + d + f;
		size += 1 + 1 + 1 + 1 + 1 + VERSION_SIZE; // chunk size chunk
		// size = size + TIMESTAMP + MOSTTRACEID + LEASTTRACEID + SPANID +
		// PARENTSPANID + FLAG + TERMINAL;
		size += SERVICETYPE + AGENTSTARTTIME;

		// startTime 4, elapsed 4, depth 4, nextSpanId 4
		size += 16;
		return size;
	}

	public byte[] writeValue() {
		byte[] agentIDBytes = BytesUtils.getBytes(agentId);
        byte[] applicationIdBytes = BytesUtils.getBytes(applicationId);
        byte[] rpcBytes = BytesUtils.getBytes(rpc);
		byte[] endPointBytes = BytesUtils.getBytes(endPoint);
        byte[] destinationIdBytes = BytesUtils.getBytes(this.destinationId);

		int bufferLength = getBufferLength(agentIDBytes.length, applicationIdBytes.length, rpcBytes.length, endPointBytes.length, destinationIdBytes.length);
		int annotationSize = getAnnotationBufferSize(annotationBoList);

		Buffer buffer = new FixedBuffer(bufferLength + annotationSize);

		buffer.put(version);

		// buffer.put(mostTraceID);
		// buffer.put(leastTraceID);

		buffer.put1PrefixedBytes(agentIDBytes);
        buffer.put1PrefixedBytes(applicationIdBytes);
        buffer.put(agentStartTime);

		buffer.put(startElapsed);
		buffer.put(endElapsed);
		// Qualifier에서 읽어서 set하므로 필요 없음.
		// buffer.put(sequence);

		buffer.put1PrefixedBytes(rpcBytes);
		buffer.put(serviceType.getCode());
		buffer.put1PrefixedBytes(endPointBytes);
        buffer.put1PrefixedBytes(destinationIdBytes);

		buffer.put(depth);
		buffer.put(nextSpanId);
		
		writeAnnotation(buffer);

		return buffer.getBuffer();
	}

	private void writeAnnotation(Buffer buffer) {
		buffer.put(annotationBoList.size());
		for (AnnotationBo annotation : annotationBoList) {
			annotation.writeValue(buffer);
		}
	}

	private int getAnnotationBufferSize(List<AnnotationBo> boList) {
		int size = 0;
		for (AnnotationBo ano : boList) {
			size += ano.getBufferSize();
		}
		// size
		size += 4;
		return size;
	}

	public int readValue(byte[] bytes, int offset) {
		Buffer buffer = new FixedBuffer(bytes, offset);

		this.version = buffer.readByte();

		// this.mostTraceID = buffer.readLong();
		// this.leastTraceID = buffer.readLong();

		this.agentId = buffer.read1PrefixedString();
        this.applicationId = buffer.read1UnsignedPrefixedString();
        this.agentStartTime = buffer.readLong();

		this.startElapsed = buffer.readInt();
		this.endElapsed = buffer.readInt();
		// Qualifier에서 읽어서 가져오므로 하지 않아도 됨.
		// this.sequence = buffer.readShort();

		this.rpc = buffer.read1UnsignedPrefixedString();
		this.serviceType = ServiceType.findServiceType(buffer.readShort());
		this.endPoint = buffer.read1UnsignedPrefixedString();
        this.destinationId = buffer.read1UnsignedPrefixedString();

		this.depth = buffer.readInt();
		this.nextSpanId = buffer.readInt();
		
		this.annotationBoList = readAnnotation(buffer);
		return buffer.getOffset();
	}

	private List<AnnotationBo> readAnnotation(Buffer buffer) {
		int count = buffer.readInt();
		List<AnnotationBo> list = new ArrayList<AnnotationBo>();
		for (int i = 0; i < count; i++) {
			AnnotationBo bo = new AnnotationBo();
			bo.readValue(buffer);
			list.add(bo);
		}
		return list;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(512);

		sb.append(this.getClass().getName()).append("={");
		sb.append("\n\tversion=").append(version).append(", agentId=").append(agentId).append(applicationId);
		sb.append("\n\tmostTraceId=").append(mostTraceId).append(", leastTraceId=").append(leastTraceId);
		sb.append("\n\tspanId=").append(spanId).append(", sequence=").append(sequence);
		sb.append("\n\tstartElapsed=").append(startElapsed).append(", endElapsed=").append(endElapsed);
		sb.append("\n\trpc=").append(rpc).append(", endPoint=").append(endPoint);
		sb.append("\n\tdepth=").append(depth);
		sb.append("\n\tnextSpanId=").append(nextSpanId);
		sb.append("\n\tannotations={");
		for (AnnotationBo a : annotationBoList) {
			sb.append("\n\t\tkey=").append(a.getKey());
			sb.append(", value=").append(a.getValue());
		}
		sb.append("\n\t}");
		sb.append("}");

		return sb.toString();
	}
}

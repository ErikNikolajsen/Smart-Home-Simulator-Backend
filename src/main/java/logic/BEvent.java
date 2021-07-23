package logic;

import java.time.LocalDateTime;

public abstract class BEvent {
	
	private LocalDateTime eventDateTime;
	
	public BEvent(LocalDateTime eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	
	public BEvent() {
	}

	public LocalDateTime getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(LocalDateTime eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

}

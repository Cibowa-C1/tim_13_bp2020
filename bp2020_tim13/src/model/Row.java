package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import observer.IListener;
import observer.IObserver;
import observer.state.ObserverStates;



public class Row implements IObserver{
	
	 private String name;
	    private Map<String, Object> fields;
	    private List<IListener> listeners;
	    
	    
	    public Row(String name) {
	        this.fields = new HashMap<>();
	        this.name = name;
	        listeners = new ArrayList<IListener>();
	    }

	    public void addField(String fieldName, Object value) {
	        this.fields.put(fieldName, value);
	    }

	    public void removeField(String fieldName) {
	        this.fields.remove(fieldName);
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Map<String, Object> getFields() {
			return fields;
		}

		public void setFields(Map<String, Object> fields) {
			this.fields = fields;
		}
		public void replaceValue(String key,Object value) {
			fields.replace(key, value);
			notifyObserver(ObserverStates.UPDATE, this);
		}

		@Override
		public void addObserver(IListener listener) {
			if(listener==null) return;
			if(this.listeners==null) 
				this.listeners = new ArrayList<>();
			if(this.listeners.contains(listener)) return;
			this.listeners.add(listener);
		}

		@Override
		public void removeObserver(IListener listener) {
			if(listener==null) return;
			if(this.listeners==null) return;
			if(!this.listeners.contains(listener)) return;
			this.listeners.remove(listener);
		}

		@Override
		public void notifyObserver(Object event, Object obj) {
			if(event==null) return;
			if(this.listeners==null) return;
			if(this.listeners.isEmpty()) return;
			for(IListener l : listeners) {
				l.update(event,obj);
			}
		}
	    
}

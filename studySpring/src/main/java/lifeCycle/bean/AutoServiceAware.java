package lifeCycle.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.stereotype.Component;

public interface AutoServiceAware  {

    void setAutoBean(Bean3 bean3) throws BeansException;
}
package thinking.in.spring;

public class MySpringEvent2 extends MySpringEvent{
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent2(String source) {
        super(source);
    }
}

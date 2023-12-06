public class UpperMiddleClass extends SourceClass{
    private GeekCatchLED gcl;
    public UpperMiddleClass (GeekCatchLED gcl){
        this.gcl = gcl;
    }
    @Override
    public void DesignTemplate() throws Exception {
        for(String p : gcl.getPortNames()) {
            System.out.println(p);
        }
        if(gcl.openPort()) {
            gcl.sendStringToComm(
                    "~128~f02"+
                            GeekCatchLED.Animations.OPEN_FROM_LEFT.getCode()+
                            GeekCatchLED.Speed.SLOW.getCode()+
                            GeekCatchLED.Delay.SLOW.getCode()+
                            GeekCatchLED.Color.BRIGHT_RED.getCode()+
                            GeekCatchLED.Font.WIDE.getCode()+
                            GeekCatchLED.name + "Just donated: " + GeekCatchLED.amount + "To say " + GeekCatchLED.message);
        }

    }
}

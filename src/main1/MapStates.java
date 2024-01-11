package main1;

public enum MapStates {
    MAP1,
    MAP2;

	public static MapStates mapState = MAP1;
    public static void SetMapState(MapStates state) {
       
        mapState= state;
        System.out.println(mapState);
        
    }
}


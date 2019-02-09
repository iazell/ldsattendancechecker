package lds.bcc.ldsattendancechecker;
public class DataModel {

    String name;
    String type;
    String version_number;
    String feature;
    String leader;
    String network;
    String status;



    public DataModel(String name, String type, String version_number, String feature, String leader, String network, String status) {
        this.name=name;
        this.type=type;
        this.version_number=version_number;
        this.feature=feature;
        this.leader=leader;
        this.network=network;
        this.status=status;

    }


    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }


    public String getVersion_number() {
        return version_number;
    }


    public String getFeature() {
        return feature;
    }

    public String getLeader() {
        return leader;
    }


    public String getNetwork() {
        return network;
    }


    public String getStatus() {
        return status;
    }



}
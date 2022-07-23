package de.tum.in.ase.eist;

import java.net.URL;
import java.util.Set;

public class SchoolProxy implements ConnectionInterface {

    private Set<String> denylistedHosts;
    private URL redirectPage;
    private Set<Integer> teacherIDs;
    private boolean authorized;
    private NetworkConnection networkConnection;

    public SchoolProxy(Set<String> denylistedHosts, URL redirectPage, Set<Integer> teacherIDs) {
        this.denylistedHosts = denylistedHosts;
        this.redirectPage = redirectPage;
        this.teacherIDs = teacherIDs;
        this.authorized = false;
        this.networkConnection = new NetworkConnection();
    }

    @Override
    public void connect(URL url) {
        for (String s : denylistedHosts) {
            if (url.getHost().equals(s) && !authorized) {

                System.err.println("Connection to " + url + " was rejected!");
                networkConnection.connect(redirectPage);
                System.out.println("Redirect call to the page " + redirectPage);


            }
        }
        networkConnection.connect(url);
    }

    @Override
    public void disconnect() {
        networkConnection.disconnect();
    }

    @Override
    public boolean isConnected() {
        return networkConnection.isConnected();
    }

    public void login(int teacherID) {

        for (Integer i : teacherIDs) {
            if (i == teacherID) {
                authorized = true;
            }
        }
    }

    public void logout() {
        authorized = false;
    }

    // TODO: Implement the SchoolProxy

}

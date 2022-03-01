package tn.happ.services;

public interface PermissionsAbilityGroup {
    public boolean isAdmin(int id);

    public boolean isCollaborator(int id);

    public boolean isCustomer(int id);

    public boolean isEmployee(int id);

    public boolean isHRmanager(int id);

    public boolean isReciponist(int id);

    public boolean isEventManager(int id);

    public boolean isCateringManager(int id);

    public boolean isAssistantChef(int id);
}

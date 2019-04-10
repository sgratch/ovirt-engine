package org.ovirt.engine.core.bll.network.host;

import org.ovirt.engine.core.bll.VdsCommand;
import org.ovirt.engine.core.bll.context.CommandContext;
import org.ovirt.engine.core.common.action.ActionReturnValue;
import org.ovirt.engine.core.common.action.ActionType;
import org.ovirt.engine.core.common.action.LockProperties;
import org.ovirt.engine.core.common.action.VdsActionParameters;

public class RefreshHostCommand extends VdsCommand<VdsActionParameters> {

    public RefreshHostCommand(VdsActionParameters parameters, CommandContext commandContext) {
        super(parameters, commandContext);
    }

    @Override
    protected void executeCommand() {
        VdsActionParameters parameters = new VdsActionParameters(getVdsId());
        LockProperties lockProperties = LockProperties.create(LockProperties.Scope.Execution);
        parameters.setLockProperties(isInternalExecution() ? lockProperties.withWaitForever() : lockProperties.withNoWait());

        ActionReturnValue returnValue = runInternalAction(ActionType.RefreshHostCapabilities, parameters);
        if (!returnValue.getSucceeded()) {
            return;
        }

        returnValue = runInternalAction(ActionType.RefreshHostDevices, parameters);
        if (!returnValue.getSucceeded()) {
            return;
        }

        setSucceeded(true);
    }
}

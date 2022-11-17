package nktns.spacex.ui.vehicles.vehicleList

import nktns.spacex.data.database.Dragon
import nktns.spacex.data.database.Rocket
import nktns.spacex.data.database.Ship

sealed class VehicleListAction {
    class ShowRocket(val rocket: Rocket) : VehicleListAction()
    class ShowShip(val ship: Ship) : VehicleListAction()
    class ShowDragon(val dragon: Dragon) : VehicleListAction()
}

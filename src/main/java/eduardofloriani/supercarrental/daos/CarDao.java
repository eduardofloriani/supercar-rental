package eduardofloriani.supercarrental.daos;

import eduardofloriani.supercarrental.dtos.CarFilterDto;
import eduardofloriani.supercarrental.models.CarModel;

import java.util.List;

public interface CarDao {

    List<CarModel> findCarsByFilters(CarFilterDto filters);

}

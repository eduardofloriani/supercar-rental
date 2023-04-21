package eduardofloriani.supercarrental.daos;

import eduardofloriani.supercarrental.dtos.CarFilterDto;
import eduardofloriani.supercarrental.models.CarModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDaoImpl implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CarModel> findCarsByFilters(CarFilterDto filters) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM cars c " +
                "LEFT JOIN rents r ON c.id = r.car_id WHERE 1=1");

        List<Object> args = new ArrayList<>();
        addOptionalCondition(sql, args, " AND c.brand = ?", Optional.ofNullable(filters.getBrand()));
        addOptionalCondition(sql, args, " AND c.model = ?", Optional.ofNullable(filters.getModel()));
        addOptionalCondition(sql, args, " AND c.body_type = ?", Optional.ofNullable(filters.getBodyType()).map(Enum::toString));
        addOptionalCondition(sql, args, " AND c.model_year >= ?", Optional.ofNullable(filters.getMinYear()));
        addOptionalCondition(sql, args, " AND c.model_year <= ?", Optional.ofNullable(filters.getMaxYear()));
        addOptionalCondition(sql, args, " AND c.transmission_type = ?", Optional.ofNullable(filters.getTransmission()).map(Enum::toString));
        addOptionalCondition(sql, args, " AND c.traction = ?", Optional.ofNullable(filters.getTraction()).map(Enum::toString));
        addOptionalCondition(sql, args, " AND c.fuel_type = ?", Optional.ofNullable(filters.getFuelType()).map(Enum::toString));
        addOptionalCondition(sql, args, " AND c.horse_power >= ?", Optional.ofNullable(filters.getMinHp()));
        addOptionalCondition(sql, args, " AND c.horse_power <= ?", Optional.ofNullable(filters.getMaxHp()));
        addOptionalCondition(sql, args, " AND c.daily_price >= ?", Optional.ofNullable(filters.getMinDailyPrice()));
        addOptionalCondition(sql, args, " AND c.daily_price <= ?", Optional.ofNullable(filters.getMaxDailyPrice()));

        LocalDate startDate = filters.getStartDate();
        LocalDate endDate = filters.getEndDate();
        if (startDate != null && endDate != null) {
            sql.append(" AND NOT EXISTS (SELECT 1 FROM rents r2 WHERE c.id = r2.car_id" +
                    " AND r2.status = 'ACTIVE' AND (r2.start_date BETWEEN ? AND ? OR r2.end_date BETWEEN ? AND ?))");
            args.add(startDate);
            args.add(endDate);
            args.add(startDate);
            args.add(endDate);
        }

        return jdbcTemplate.query(sql.toString(),
                args.toArray(),
                new BeanPropertyRowMapper<>(CarModel.class));
    }

    private void addOptionalCondition(StringBuilder sql, List<Object> args, String clause, Optional<?> optionalValue) {
        optionalValue.filter(value -> (value instanceof String && !((String) value).isEmpty()) || (value instanceof Number && ((Number) value).doubleValue() > 0))
                .ifPresent(value -> {
                    sql.append(clause);
                    args.add(value);
                });
    }

}

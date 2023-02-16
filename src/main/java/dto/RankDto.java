package dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RankDto {
	UNRANKED(0, false, 0),
	FIFTH(3, false, 5_000),
	FOURTH(4, false, 50_000),
	THIRD(5, false, 1_500_000),
	SECOND(5, true, 30_000_000),
	FIRST(6, false, 2_000_000_000);

	private final int correctNumber;
	private final boolean bonus;
	private final int winningAmount;

	RankDto(int correctNumber, boolean bonus, int winningAmount) {
		this.correctNumber = correctNumber;
		this.bonus = bonus;
		this.winningAmount = winningAmount;
	}

	public static List<RankDto> getRankDtos() {
		return Arrays.stream(RankDto.values())
			.filter(rank -> rank != RankDto.UNRANKED)
			.collect(Collectors.toUnmodifiableList());
	}

	public int getCorrectNumber() {
		return correctNumber;
	}

	public boolean isBonus() {
		return bonus;
	}

	public int getWinningAmount() {
		return winningAmount;
	}
}

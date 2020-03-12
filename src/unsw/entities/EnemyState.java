package unsw.entities;

/**
 * Enemy State Interface
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */
public interface EnemyState {
	
	public EnemyState makeNormal();
	
	public EnemyState makeScared();
	
	public void  moveEnemy(Player player, Enemy enemy);
	
}

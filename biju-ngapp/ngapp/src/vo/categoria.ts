
export class Categoria {

    public readonly BIJUTERIA: String = "Bijuteria";
	public readonly SEMIJOIA: String = "Semij\u00F3ia";
	public readonly JOIA: String = "J\u00F3ia";

	public categoria: String
    
	public getCategorias(): Categoria[] {
		let categorias: Categoria[];

		let biju: Categoria = new Categoria();
		let semi: Categoria = new Categoria();
		let joia: Categoria = new Categoria();
		biju.categoria = this.BIJUTERIA;
		semi.categoria = this.SEMIJOIA;
		joia.categoria = this.JOIA;
	
		categorias.push(biju);
		categorias.push(semi);
		categorias.push(joia);

		return categorias;
	}
 
}